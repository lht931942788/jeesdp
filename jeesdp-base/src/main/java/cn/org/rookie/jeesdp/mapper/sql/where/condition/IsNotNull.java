package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.Condition;

public class IsNotNull extends Condition {

    public IsNotNull(String columnName) {
        super(columnName);
    }

    @Override
    public String render() {
        return columnName + " IS NOT NULL";
    }

}
