package cn.org.rookie.jeesdp.core.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LIHAITAO
 */

public class SQLUtils {

    static final Pattern pattern = Pattern.compile("#\\{([^{}]+?)}");
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_21);

    static {
    }

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public static void main(String[] args) throws IOException, TemplateException {

        String src = "<#if demo??>${demo} = #{demo}</#if>";
        parse("demo", src);

    }

    public static String replace(String script) {
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();
        int count = matcher.groupCount();
        Object[] o = new Object[count];
        while (matcher.find()) {
            String group = matcher.group();
            matcher.appendReplacement(sb, ":demo");
            Matcher nameMatcher = Pattern.compile("[a-zA-Z]+").matcher(group);
            if (nameMatcher.find()) {
                String name = nameMatcher.group();
                System.out.println(name);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void parse(String name, String script) {
        try {
            Template template = new Template(name, replace(script), CONFIGURATION);
            Map<String, Object> model = new HashMap<>();
            model.put("demo", "1");
            StringWriter out = new StringWriter();
            template.process(model, out);
            System.out.println(out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
