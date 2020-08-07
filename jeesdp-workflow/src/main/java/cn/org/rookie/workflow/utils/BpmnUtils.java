package cn.org.rookie.workflow.utils;

import cn.org.rookie.workflow.constant.Bpmn;
import cn.org.rookie.workflow.definitions.Process;
import cn.org.rookie.workflow.definitions.*;
import cn.org.rookie.workflow.entity.Model;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BpmnUtils {

    public static Element getElementById(Element root, String id) {
        Iterator<Element> it = root.elementIterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (id.equals(element.attributeValue("id"))) {
                return element;
            } else {
                return getElementById(element, id);
            }
        }
        return null;
    }

    public static Process convert(Model model) {
        return loadNodes(processElement(read(model.getResource().getXml())), new Process());
    }

    public static Process convert(File file) {
        Element element = processElement(read(file));
        Process process = new Process();
        convert(element, process);
        return loadNodes(element, process);
    }

    public static void convert(Element element, Object bean) {
        List<Attribute> attributes = element.attributes();
        Map<String, String> attr = new HashMap<>();
        attributes.forEach(attribute -> attr.put(attribute.getName(), attribute.getValue()));
        try {
            BeanUtils.populate(bean, attr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Element processElement(Document document) {
        return document.getRootElement().element("process");
    }

    public static Document read(String content) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(new String(content.getBytes(Charset.defaultCharset()), StandardCharsets.UTF_8).getBytes()));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document read(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Process loadNodes(Element root, Process process) {
        Map<String, AbstractModel> nodes = new HashMap<>();
        List<Element> elements = root.elements();
        elements.forEach(element -> {
            switch (element.getName()) {
                case Bpmn.TagName.START_EVENT:
                    StartEvent startEvent = new StartEvent();
                    convert(element, startEvent);
                    startEvent.setOutgoings(getLineIds(element, Bpmn.TagName.OUTGOING));
                    process.setStartId(startEvent.getId());
                    nodes.put(element.attributeValue("id"), startEvent);
                    break;
                case Bpmn.TagName.USER_TASK:
                    UserTask userTask = new UserTask();
                    convert(element, userTask);
                    userTask.setIncomings(getLineIds(element, Bpmn.TagName.INCOMING));
                    userTask.setOutgoings(getLineIds(element, Bpmn.TagName.OUTGOING));
                    nodes.put(element.attributeValue("id"), userTask);
                    break;
                case Bpmn.TagName.SEQUENCE_FLOW:
                    SequenceFlow sequenceFlow = new SequenceFlow();
                    convert(element, sequenceFlow);
                    nodes.put(element.attributeValue("id"), sequenceFlow);
                    break;
                case Bpmn.TagName.END_EVENT:
                    EndEvent endEvent = new EndEvent();
                    convert(element, endEvent);
                    nodes.put(element.attributeValue("id"), endEvent);
                    break;
                default:
                    break;
            }
        });
        process.putModels(nodes);
        return process;
    }

    public static List<String> getLineIds(Element root, String tageName) {
        List<String> ids = new ArrayList<>();
        List<Element> elements = root.elements(tageName);
        elements.forEach(element -> ids.add(element.getText()));
        return ids;
    }

}
