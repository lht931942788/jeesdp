package cn.org.rookie.jeesdp.mapper.sql;

public abstract class SingleCondition extends Condition {


    public String render() {
        return columnName + " " + getTerm() + " " + getPrefix() + "{wrapper.params." + columnName + "}";
    }

    public abstract String getTerm();
}
