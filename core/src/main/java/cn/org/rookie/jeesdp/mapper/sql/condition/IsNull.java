package cn.org.rookie.jeesdp.mapper.sql.condition;

import cn.org.rookie.jeesdp.mapper.sql.Condition;

public class IsNull extends Condition {

    @Override
    public String render() {
        return columnName + " IS NULL";
    }
}
