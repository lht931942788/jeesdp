package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.SingleCondition;

public class Eq extends SingleCondition {

    public Eq(String columnName) {
        super(columnName);
    }

    @Override
    public String getTerm() {
        return "=";
    }
}
