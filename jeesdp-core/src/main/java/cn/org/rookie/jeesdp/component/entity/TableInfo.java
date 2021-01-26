package cn.org.rookie.jeesdp.component.entity;

import java.util.Set;

public class TableInfo {

    private String name;
    private ColumnInfo primary;
    private Set<ColumnInfo> columnInfos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnInfo getPrimary() {
        return primary;
    }

    public void setPrimary(ColumnInfo primary) {
        this.primary = primary;
    }

    public Set<ColumnInfo> getColumns() {
        return columnInfos;
    }

    public void setColumns(Set<ColumnInfo> columnInfos) {
        this.columnInfos = columnInfos;
    }

}
