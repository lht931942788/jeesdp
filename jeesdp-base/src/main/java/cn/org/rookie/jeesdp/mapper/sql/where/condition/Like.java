package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.SingleCondition;

public class Like extends SingleCondition {

    public Like(String columnName) {
        super(columnName);
    }

    @Override
    public String getTerm() {
        return "LIKE";
    }

}
