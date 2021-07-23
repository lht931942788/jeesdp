package cn.org.rookie.jeesdp.mapper.sql.condition;

import cn.org.rookie.jeesdp.mapper.sql.Condition;

public class IsNotNull extends Condition {

    @Override
    public String render() {
        return columnName + " IS NOT NULL";
    }

}
