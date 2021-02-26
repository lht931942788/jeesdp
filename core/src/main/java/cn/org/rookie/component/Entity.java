package cn.org.rookie.component;

import cn.org.rookie.entity.ColumnInfo;
import cn.org.rookie.entity.TableInfo;
import cn.org.rookie.mapper.annotation.Column;
import cn.org.rookie.mapper.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Entity {

    private final TableInfo tableInfo;
    private final Class<?> entity;
    private final Map<String, String> sql = new HashMap<>();
    private final List<String> defaultWhere = new ArrayList<>();
    private String flag;
    private List<String> where;

    public static final Pattern humpPattern = Pattern.compile("[A-Z]");

    public Entity insert() {
        where = new ArrayList<>();
        flag = "insert";
        return this;
    }

    public Entity delete() {
        where = new ArrayList<>();
        flag = "delete";
        return this;
    }

    public Entity update() {
        where = new ArrayList<>();
        flag = "update";
        return this;
    }

    public Entity select() {
        where = new ArrayList<>();
        flag = "select";
        return this;
    }

    public Entity byPrimary() {
        where.add(tableInfo.getName() + "." + tableInfo.getPrimary().getColumnName() + " = " + ":" + tableInfo.getPrimary().getName());
        return this;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public Class<?> getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        String sql = this.sql.get(flag);
        if (defaultWhere.size() > 0) {
            sql = sql + " WHERE " + String.join("", defaultWhere);
        }
        if (where.size() > 0) {
            if (defaultWhere.size() > 0) {
                sql = sql + " AND " + String.join("", where);
            } else {
                sql = sql + " WHERE " + String.join("", where);
            }
        }
        return sql;
    }

    public Entity(Class<?> entity) {
        this.entity = entity;
        tableInfo = new TableInfo();
        Table table = entity.getAnnotation(Table.class);
        String tableName = table.value();
        if (tableName.isEmpty()) {
            tableName = humpToUnderline(entity.getSimpleName());
        }
        tableInfo.setName(tableName);

        List<String> fields = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        List<String> selectColumns = new ArrayList<>();
        List<String> updateColumns = new ArrayList<>();

        Field[] entityFields = entity.getDeclaredFields();
        for (Field field : entityFields) {
            if (!":$staticClassInfo,:__$stMC,:metaClass,:$callSiteArray".contains(field.getName())) {
                ColumnInfo columnInfo = new ColumnInfo();
                String name = field.getName();
                columnInfo.setName(name);

                Column column = field.getAnnotation(Column.class);
                String columnName;
                if (column == null) {
                    columnName = humpToUnderline(name);
                    updateColumns.add(tableName + "." + columnName + " = " + ":" + name);
                } else {
                    columnName = column.value();
                    if (columnName.isEmpty()) {
                        columnName = humpToUnderline(name);
                    }
                    if (column.primary()) {
                        tableInfo.setPrimary(columnInfo);
                    } else {
                        updateColumns.add(tableName + "." + columnName + " = " + ":" + name);
                    }
                }
                columnInfo.setColumnName(columnName);
                fields.add(":" + name);
                columns.add(tableName + "." + columnName);
                selectColumns.add(tableName + "." + columnName + " AS " + "\"" + name + "\"");
            }
        }
        sql.put("insert", String.format("INSERT INTO %s (%s) VALUE (%s)", tableName, String.join(", ", columns), String.join(", ", fields)));
        sql.put("delete", String.format("DELETE FROM %s", tableName));
        sql.put("update", String.format("UPDATE %s SET %s", tableName, String.join(", ", updateColumns)));
        sql.put("select", String.format("SELECT %s FROM %s", String.join(", ", selectColumns), tableName));
    }

    private static String humpToUnderline(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
