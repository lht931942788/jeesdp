package cn.org.rookie.jeesdp.mapper.sql.condition;

import cn.org.rookie.jeesdp.mapper.sql.SingleCondition;

public class NotEqual extends SingleCondition {

    @Override
    public String getTerm() {
        return "!=";
    }
}
