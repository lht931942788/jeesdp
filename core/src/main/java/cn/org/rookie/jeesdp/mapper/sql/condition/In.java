package cn.org.rookie.jeesdp.mapper.sql.condition;

import cn.org.rookie.jeesdp.mapper.sql.Condition;

public class In extends Condition {

    @Override
    public String render() {
        return columnName + " IN <foreach collection=\"wrapper.params." + columnName + "\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" + prefix + "{item}</foreach>";
    }
}
