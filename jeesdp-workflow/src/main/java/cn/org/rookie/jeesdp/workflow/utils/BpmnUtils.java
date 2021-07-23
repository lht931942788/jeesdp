package cn.org.rookie.jeesdp.workflow.utils;

import cn.org.rookie.jeesdp.workflow.constant.Bpmn;
import cn.org.rookie.jeesdp.workflow.definitions.*;
import cn.org.rookie.jeesdp.jeesdp.workflow.definitions.*;
import cn.org.rookie.jeesdp.workflow.definitions.Process;
import cn.org.rookie.jeesdp.workflow.entity.Model;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;
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
        return loadNodes(element, convert(element, Process.class));
    }

    public static <T> T convert(Element element, Class<T> type) {
        List<Attribute> attributes = element.attributes();
        Map<String, Object> attr = new HashMap<>();
        attributes.forEach(attribute -> attr.put(attribute.getName(), attribute.getValue()));
        return populate(attr, type);
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
                    StartEvent startEvent = convert(element, StartEvent.class);
                    startEvent.setOutgoings(getLineIds(element, Bpmn.TagName.OUTGOING));
                    process.setStartId(startEvent.getId());
                    nodes.put(element.attributeValue("id"), startEvent);
                    break;
                case Bpmn.TagName.USER_TASK:
                    UserTask userTask = convert(element, UserTask.class);
                    userTask.setIncomings(getLineIds(element, Bpmn.TagName.INCOMING));
                    userTask.setOutgoings(getLineIds(element, Bpmn.TagName.OUTGOING));
                    nodes.put(element.attributeValue("id"), userTask);
                    break;
                case Bpmn.TagName.SEQUENCE_FLOW:
                    nodes.put(element.attributeValue("id"), convert(element, SequenceFlow.class));
                    break;
                case Bpmn.TagName.END_EVENT:
                    nodes.put(element.attributeValue("id"), convert(element, EndEvent.class));
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

    private static <T> T populate(Map<String, Object> source, Class<T> type) {

        try {
            T instance = type.getConstructor().newInstance();
            getAllField(type).forEach(field -> {
                field.setAccessible(true);
                try {
                    field.set(instance, source.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static Map<String, Object> describe(Object bean) {
        Map<String, Object> map = new LinkedHashMap<>();

        getAllField(bean.getClass()).forEach(field -> {
            try {
                map.put(field.getName(), field.get(bean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    private static List<Field> getAllField(Class<?> type) {

        List<Field> fields = new ArrayList<>();

        Class<?> superclass = type.getSuperclass();
        if (!superclass.equals(Object.class)) {
            fields.addAll(getAllField(superclass));
        }
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        return fields;
    }
}
