package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.SingleCondition;

public class NotEq extends SingleCondition {
    public NotEq(String columnName) {
        super(columnName);
    }

    @Override
    public String getTerm() {
        return "!=";
    }
}
