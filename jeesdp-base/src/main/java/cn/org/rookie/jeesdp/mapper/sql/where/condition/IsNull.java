package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.Condition;

public class IsNull extends Condition {

    public IsNull(String columnName) {
        super(columnName);
    }

    @Override
    public String render() {
        return columnName + " IS NULL";
    }
}
