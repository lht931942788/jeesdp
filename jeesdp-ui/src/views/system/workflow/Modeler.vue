2
<template>
    <div style="height: 100%;width: 100%;">
        <div ref="container" style="height: 100%;width: 100%;border: solid 1px #CCC;">
            <div class="workflow-toolbar">
                <el-button type="text" title="保存" @click="saveXML" icon="fa fa-floppy-o"></el-button>
                <el-button type="text" title="后退" @click="undo" icon="fa fa-reply"></el-button>
                <el-button type="text" title="前进" @click="redo" icon="fa fa-share"></el-button>
            </div>
        </div>
        <div class="properties">
            <div style="width: 100%">
                <h1>{{node&&node.name?node.name:node.id}}</h1>
            </div>
            <el-form v-if="nodeType === 'bpmn:Process'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
            </el-form>

            <el-form v-if="nodeType === 'bpmn:StartEvent'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
            </el-form>
            <el-form v-if="nodeType === 'bpmn:UserTask'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
                <el-form-item label="审核类型">
                    <el-select v-model="node.auditType" placeholder="请选择" style="width: 100%">
                        <el-option value="1" label="用户"></el-option>
                        <el-option value="2" label="部门"></el-option>
                        <el-option value="3" label="角色"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-if="node.auditType === '1'" label="用户">
                    <el-input placeholder="审批人" v-model="node.userIds"></el-input>
                </el-form-item>
            </el-form>
            <el-form v-if="nodeType === 'bpmn:SequenceFlow'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
                <el-form-item label="表达式">
                    <el-input placeholder="表达式" v-model="node.expression"></el-input>
                </el-form-item>
            </el-form>

            <el-form v-if="nodeType === 'bpmn:SendTask'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
            </el-form>

            <el-form v-if="nodeType === 'bpmn:ServiceTask'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
            </el-form>
            <el-form v-if="nodeType === 'bpmn:EndEvent'" style="width: 100%" label-position="top">
                <el-form-item label="名称">
                    <el-input placeholder="名称" v-model="node.name"></el-input>
                </el-form-item>
            </el-form>
            <el-button @click="saveNode" type="primary">确定</el-button>
        </div>
    </div>
</template>
<style>
    .workflow-toolbar {
        position: absolute;
        top: 110px;
        left: 360px;
        z-index: 100;
        background: #FAFAFA;
        border: solid 1px #CCC;
        border-radius: 2px;
    }

    .properties {
        width: 250px;
        position: absolute;
        top: 81px;
        bottom: 40px;
        right: 18px;
        border-left: #CCC solid 1px;
        border-right: #CCC solid 1px;
        padding: 10px;
        background-color: #f8f8f8;
        overflow: auto;
    }

    .workflow-toolbar {
        padding: 0 10px;
    }

    .el-button--text {
        font-size: 16px !important;
        color: black !important;
    }

    .bjs-powered-by {
        display: none !important;
    }
</style>
<script>
    import BpmnModeler from '../../../bpmn/Modeler'
    import {getBusinessObject} from 'bpmn-js/lib/util/ModelUtil';
    import translate from '../../../bpmn/translate'
    import wf from '../../../bpmn/extensions/wf.json';
    import 'bpmn-js/dist/assets/diagram-js.css';
    import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
    import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css';
    import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css';

    export default {
        name: 'Modeler',
        data: function () {
            return {
                bpmnModeler: null,
                diagramXML: null,
                node: {},
                title: '',
                element: null,
                nodeType: 'bpmn:Process',
                id: ''
            };
        },
        methods: {
            demo() {
                console.log(this.diagramXML)
            },
            saveNode() {
                this.addAttr(this.node);
            },
            addAttr(data) {
                let modeling = this.bpmnModeler.get('modeling');
                modeling.updateProperties(this.element, data);
            },
            addElement(nodeName, data) {
                let businessObject = getBusinessObject(this.element);
                let moddle = this.bpmnModeler.get('moddle');
                let modeling = this.bpmnModeler.get('modeling');
                let extensionElements = businessObject.extensionElements || moddle.create('bpmn:ExtensionElements');
                let extensionElement = getExtensionElement(businessObject, nodeName);
                if (!extensionElement) {
                    extensionElement = moddle.create(nodeName);
                    extensionElements.get('values').push(extensionElement);
                }
                for (let key in data) {
                    extensionElement[key] = data[key];
                }
                modeling.updateProperties(this.element, {
                    extensionElements,
                });
            },
            saveSVG() {
                this.bpmnModeler.saveSVG({
                    format: true
                }, (err, xml) => {
                    this.diagramXML = xml;
                    console.log(xml)
                });
            },
            saveXML() {
                this.bpmnModeler.saveXML((err, xml) => {
                    this.$ajax.post("/workflow/updateProcessContent", {
                        content: xml,
                        id: this.id
                    })
                });
            },
            selectedChange(element) {
                this.node = {};
                if (this.element !== null) {
                    this.saveNode();
                }
                this.element = element;
                this.nodeType = element.type;
                this.copy(element);
            },
            copy(element) {
                let businessObject = element.businessObject;
                switch (element.type) {
                    case 'bpmn:Process':
                        this.node = {
                            id: businessObject.id,
                            name: businessObject.name ? businessObject.name : '',
                        };
                        break;
                    case 'bpmn:StartEvent':
                        this.node = {
                            id: businessObject.id,
                            name: businessObject.name ? businessObject.name : '',
                        };
                        break;
                    case 'bpmn:UserTask':
                        this.node = {
                            id: businessObject.id,
                            name: businessObject.name ? businessObject.name : '',
                            userIds: businessObject.userIds ? businessObject.userIds : '',
                            auditType: businessObject.auditType ? businessObject.auditType : '',
                        };
                        break;
                    case 'bpmn:SequenceFlow':
                        this.node = {
                            id: businessObject.id,
                            name: businessObject.name ? businessObject.name : '',
                            expression: businessObject.expression ? businessObject.expression : '',
                        };
                        break;
                    case 'bpmn:ServiceTask':
                        this.node = {
                            id: businessObject.id,
                            name: businessObject.name ? businessObject.name : '',
                        };
                        break;
                    case 'bpmn:EndEvent':
                        this.node = {
                            id: businessObject.id,
                            name: businessObject.name ? businessObject.name : '',
                        };
                        break;

                }
            },
            redo() {
                this.bpmnModeler.get('commandStack').redo();
            },
            undo() {
                this.bpmnModeler.get('commandStack').undo();
            }
        },
        mounted() {
            let self = this;
            this.bpmnModeler = new BpmnModeler({
                container: this.$refs.container,
                moddleExtensions: {
                    wf: wf,
                },
                additionalModules: [
                    translate
                ],
                keyboard: {
                    bindTo: window
                }
            });
            this.bpmnModeler.on('import.done', () => {
                self.bpmnModeler.get('canvas').zoom('fit-viewport');
                console.log(self.bpmnModeler.get('elementRegistry'))
                self.selectedChange(self.bpmnModeler.get('elementRegistry').get(this.id));
            });

            this.bpmnModeler.on('selection.changed', function (event) {
                if (event.newSelection.length === 1) {
                    if (event.newSelection[0].type !== 'label') {
                        self.selectedChange(event.newSelection[0]);
                    }
                }
            });

            this.bpmnModeler.on('element.click', function (event) {
                if (event.element.type === 'bpmn:Process') {
                    self.selectedChange(event.element)
                }
            });

            this.bpmnModeler.on('element.marker.update', function (event) {
                console.log(event)
            });

            self.$ajax.get("/workflow/getProcessById?id=" + this.$route.query.id).then(result => {
                this.id = result.id;
                this.bpmnModeler.importXML(result.content);
            });
        },
        beforeDestroy: function () {
            this.bpmnModeler.destroy();
        },
    };

    function getExtensionElement(element, type) {
        if (!element.extensionElements) {
            return;
        }
        return element.extensionElements.values.filter((extensionElement) => {
            return extensionElement.$instanceOf(type);
        })[0];
    }

</script>
