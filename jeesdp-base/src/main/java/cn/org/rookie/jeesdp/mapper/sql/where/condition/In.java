package cn.org.rookie.jeesdp.mapper.sql.where.condition;

import cn.org.rookie.jeesdp.mapper.sql.where.Condition;

public class In extends Condition {
    public In(String columnName) {
        super(columnName);
    }

    @Override
    public String render() {
        return columnName + " IN <foreach collection=\"wrapper.params." + columnName + "\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" + prefix + "{item}</foreach>";
    }
}
