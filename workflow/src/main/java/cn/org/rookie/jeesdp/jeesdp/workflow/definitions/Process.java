package cn.org.rookie.jeesdp.jeesdp.workflow.definitions;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class Process {

    private String id;
    private String name;
    private String params;
    private String startId;
    private Map<String, AbstractModel> nodes = new HashMap<>();

    public void putModels(Map<String, AbstractModel> nodes) {
        this.nodes.putAll(nodes);
    }

    public AbstractModel getNode(String nodeId) {
        return nodes.get(nodeId);
    }

}
