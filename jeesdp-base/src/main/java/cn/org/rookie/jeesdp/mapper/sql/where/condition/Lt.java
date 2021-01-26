package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.SingleCondition;

public class Lt extends SingleCondition {
    public Lt(String columnName) {
        super(columnName);
    }

    @Override
    public String getTerm() {
        return "&lt;";
    }
}
