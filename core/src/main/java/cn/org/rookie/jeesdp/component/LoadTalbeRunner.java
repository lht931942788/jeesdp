package cn.org.rookie.jeesdp.component;

import cn.org.rookie.jeesdp.utils.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LIHAITAO
 * @date 2021-04-27 8:32
 */

@Component
public class LoadTalbeRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Record record = new Record();

        List<Record> records = new ArrayList<>();

        Set<String> columns = new LinkedHashSet<>();
        Set<String> tables = new LinkedHashSet<>();
        Set<String> conditions = new LinkedHashSet<>();
        String columnsString = StringUtils.join(", ", columns);

        StringBuilder insert = new StringBuilder("INSERT");
        insert.append(" ").append("INTO").append(" ");
        insert.append("TABLE");
        insert.append(" ").append("(");
        insert.append("<tirm suffixOverrides=\",\">");
        String ifScript = "<if test=\"%s != null and %s != ''\">%s</if>";
        for (String column : columns) {
            insert.append(String.format(ifScript, column, column, column)).append(",").append(" ");
        }
        insert.append("</tirm>");
        insert.append(")").append(" ").append("VALUES").append("(");
        insert.append("<tirm suffixOverrides=\",\">");
        for (String column : columns) {
            insert.append(String.format(ifScript, column, column, "#{" + column + "}"));
            insert.append("#").append("{").append(column).append("}").append(",").append(" ");
        }
        insert.append("</tirm>");
        insert.append(")");

        StringBuilder delete = new StringBuilder("DELETE");
        delete.append(" ").append("FROM").append(" ").append("TABLE");

        StringBuilder select = new StringBuilder("SELECT");
        select.append(" ");
        select.append(StringUtils.join("," + " ", columns));
        select.append(" ").append("FROM").append(" ");
        select.append(StringUtils.join(tables));
        select.append(" ").append("WHERE").append(" ");
        select.append(StringUtils.join(" AND ", conditions));


    }
}
