package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.SingleCondition;

public class Gt extends SingleCondition {
    public Gt(String columnName) {
        super(columnName);
    }

    @Override
    public String getTerm() {
        return "&gt;";
    }
}
