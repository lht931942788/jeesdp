package cn.org.rookie.jeesdp.core.entity;

import java.util.List;

public class Tree {
    private String id;
    private String value;
    private String label;
    private String parent;
    private List<Tree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", parent='" + parent + '\'' +
                ", children=" + children +
                '}';
    }
}
