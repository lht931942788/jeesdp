<template>
  <el-row style="height: inherit">
    <el-col :span="4" style="padding: 20px;border: 1px solid #EBEEF5; box-sizing:border-box;">
      <el-row>
        <el-button-group style="width: 100%;">
          <el-button style="width: 50%" type="primary" @click="onAdd">新增</el-button>
          <el-button style="width: 50%" type="primary" @click="onAdd">保存</el-button>
        </el-button-group>
        <el-input v-model="label" placeholder="请输入名称" style="margin-top: 20px" table></el-input>
      </el-row>
      <el-tree ref="tree" :data="data" :filter-node-method="filterNode" accordion style="margin-top: 20px"
               @node-click="onNodeClick">
        <template #default="{ node, data }">
          <div class="tree-node" style="line-height: 40px" @mouseout="mouseout($event)">
          <span @mouseover="mouseover">
            {{ node.label + '(' + data.name + ')' }}
          </span>
            <span ref="delete" style="display: none; padding-left: 5px">
            <el-button icon="el-icon-delete" size="small" title="删除" type="text"
                       @click="onModelDelete(node, data)"></el-button>
          </span>
          </div>
        </template>
      </el-tree>
    </el-col>
    <el-col :span="20" style="border: 1px solid #EBEEF5; box-sizing:border-box; padding: 20px">
      <el-form :model="model" label-position="rigth" label-suffix="：" label-width="120px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="中文名称">
              <el-input v-model="model.label"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="表名">
              <el-input v-model="model.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="查看权限">
              <el-select v-model="model.view_type" style="width: 100%">
                <el-option key="1" label="所有用户" value="1"></el-option>
                <el-option key="2" label="当前用户" value="2"></el-option>
                <el-option key="3" label="当前部门" value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="逻辑删除">
              <el-switch v-model="model.logical_delete"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-divider></el-divider>
      <el-tabs v-model="active">
        <el-tab-pane label="字段管理" name="字段管理">
          <el-button icon="el-icon-plus" title="新增" type="primary" @click="addColumns"></el-button>
          <el-table :data="model.table" border style="margin-top: 20px">
            <el-table-column align="center" fixed="left" header-align="center" label="#" type="index"></el-table-column>
            <el-table-column fixed="left" header-align="center" label="中文名称" prop="label" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-input v-model="row.label"></el-input>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column fixed="left" header-align="center" label="名称" prop="name" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-input v-model="row.name"></el-input>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="类型" prop="type" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-select v-model="row.type" style="width: 100%">
                    <el-option key="VARCHAR" label="VARCHAR" value="VARCHAR"></el-option>
                    <el-option key="DATE" label="DATE" value="DATE"></el-option>
                    <el-option key="INT" label="INT" value="INT"></el-option>
                    <el-option key="THINT" label="THINT" value="THINT"></el-option>
                  </el-select>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="长度" prop="length" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-input v-model="row.length"></el-input>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="默认值类型" prop="default_type" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-select v-model="row.default_type" style="width: 100%">
                    <el-option key="1" label="指定值" value="1"></el-option>
                    <el-option key="2" label="雪花ID" value="2"></el-option>
                    <el-option key="3" label="UUID" value="3"></el-option>
                    <el-option key="4" label="当前用户" value="4"></el-option>
                    <el-option key="5" label="当前时间" value="5"></el-option>
                  </el-select>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="默认值" prop="default_value" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-input v-model="row.default_value"></el-input>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="显示类型" prop="show_type" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-select v-model="row.show_type" style="width: 100%">
                    <el-option key="1" label="全部展示" value="1"></el-option>
                    <el-option key="2" label="全部隐藏" value="2"></el-option>
                    <el-option key="3" label="列表展示" value="3"></el-option>
                    <el-option key="4" label="表单展示" value="4"></el-option>
                  </el-select>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="主键" prop="primary_key" width="150">
              <template #default="{ row, column, $index }">
                <el-switch v-model="row.primary_key" :disabled="!row.editable"></el-switch>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="表单类型" prop="default_type" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-select v-model="row.default_type" style="width: 100%">
                    <el-option key="1" label="指定值" value="1"></el-option>
                    <el-option key="2" label="雪花ID" value="2"></el-option>
                    <el-option key="3" label="UUID" value="3"></el-option>
                    <el-option key="4" label="当前用户" value="4"></el-option>
                    <el-option key="5" label="当前时间" value="5"></el-option>
                  </el-select>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column header-align="center" label="表单校验" prop="default_type" width="150">
              <template #default="{ row, column, $index }">
                <template v-if="row.editable">
                  <el-select v-model="row.default_type" style="width: 100%">
                    <el-option key="1" label="指定值" value="1"></el-option>
                    <el-option key="2" label="雪花ID" value="2"></el-option>
                    <el-option key="3" label="UUID" value="3"></el-option>
                    <el-option key="4" label="当前用户" value="4"></el-option>
                    <el-option key="5" label="当前时间" value="5"></el-option>
                  </el-select>
                </template>
                <template v-else>
                  {{ row[column.property] }}
                </template>
              </template>
            </el-table-column>
            <el-table-column align="center" fixed="right" header-align="center" label="操作" prop="label" width="140px">
              <template #default="{ row, column, $index }">
                <el-button-group>
                  <el-button :icon="!row.editable ? 'el-icon-edit' : 'fa fa-save'" :title="row.editable ? '保存' : '修改'"
                             type="primary" @click="row.editable = !row.editable">
                  </el-button>
                  <el-button icon="el-icon-delete" title="删除" type="danger"
                             @click="onTableClick(row, $index)"></el-button>
                </el-button-group>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="关联关系" name="关联关系">
          <el-button table type="primary" @click="addColumns">新增</el-button>
          <el-table :data="model.relationship" border style="margin-top: 20px">
            <el-table-column align="center" fixed="right" header-align="center" label="操作" prop="label" width="140px">
              <template #default="{ row, column, $index }">
                <el-button-group>
                  <el-button :icon="!row.editable ? 'el-icon-edit' : 'fa-save'" :title="row.editable ? '保存' : '修改'"
                             type="primary" @click="row.editable = !row.editable"></el-button>
                  <el-button icon="el-icon-delete" title="删除" type="danger"
                             @click="onTableClick(row, $index)"></el-button>
                </el-button-group>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="查询设置" name="查询设置"></el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Model",
  data() {
    return {
      label: '',
      active: '字段管理',
      model: {
        table: [],
        relationship: [],
        form: [],
        search: []
      },
      treeNodeStyle: {
        display: 'none'
      },
      data: [
        {
          label: '测试',
          name: 'demo',
          table: [
            {
              label: '主键',
              name: 'id',
              type: 'INT',
              length: '50',
              show_type: '2',
              editable: false,
            }, {
              label: '创建人',
              name: 'create_by',
              type: 'INT',
              length: '50',
              show_type: '2',
              editable: false,
            }, {
              label: '创建时间',
              name: 'create_time',
              type: 'DATE',
              show_type: '2',
              editable: false,
            }, {
              label: '修改人',
              name: 'create_by',
              type: 'INT',
              length: '50',
              show_type: '2',
              editable: false,
            }, {
              label: '修改时间',
              name: 'create_time',
              type: 'DATE',
              show_type: '2',
              editable: false,
            }, {
              label: '删除标识',
              name: 'is_delete',
              type: 'THINT',
              length: '1',
              show_type: '2',
              editable: false,
            }
          ]
        }
      ],
      defautlModel: [
        {
          label: '主键',
          name: 'id',
          type: 'INT',
          length: '50',
          show_type: '2',
          default_type: '2',
          editable: false,
        }, {
          label: '创建人',
          name: 'create_by',
          type: 'INT',
          length: '50',
          default_type: '4',
          show_type: '2',
          editable: false,
        }, {
          label: '创建时间',
          name: 'create_time',
          type: 'DATE',
          default_type: '2',
          show_type: '5',
          editable: false,
        }, {
          label: '修改人',
          name: 'create_by',
          type: 'INT',
          length: '50',
          default_type: '4',
          show_type: '2',
          editable: false,
        }, {
          label: '修改时间',
          name: 'create_time',
          type: 'DATE',
          default_type: '5',
          show_type: '2',
          editable: false,
        }, {
          label: '删除标识',
          name: 'is_delete',
          type: 'THINT',
          length: '1',
          default_type: '1',
          default_value: 1,
          show_type: '2',
          editable: false,
        }
      ],
      romovedColumns: []
    }
  },
  methods: {
    onAdd() {
      this.model.table = this.defautlModel
    },
    onNodeClick(data, node, xx) {
      this.model = data;
    },
    onTableClick(row, index) {
      this.model.table.splice(index, 1);
      this.romovedColumns.push(row);
    },
    addColumns() {
      this.model.table.splice(this.model.table.length - 5, 0, {
        label: '',
        name: '',
        type: '',
        length: '',
        show_type: '',
        default_type: '',
        editable: true,
      })
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    mouseout(e) {
      if (e.target.getAttribute('class') === 'tree-node') {
        this.$refs.delete.style.display = 'none';
      }
    },
    mouseover() {
      this.$refs.delete.style.display = 'inline-block';
    },
    onModelDelete(node, data) {
      console.log(node, data)
    }
  },
  watch: {
    label(val) {
      this.$refs.tree.filter(val);
    }
  },
}
</script>

<style scoped>

</style>
