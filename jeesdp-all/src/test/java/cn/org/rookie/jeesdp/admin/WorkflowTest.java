/*
package cn.org.rookie.admin;

import cn.org.rookie.workflow.ProcessEngine;
import cn.org.rookie.workflow.configuration.EnableWorkflowConfiguration;
import cn.org.rookie.workflow.service.impl.AbstractProcessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@EnableWorkflowConfiguration
public class WorkflowTest {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private AbstractProcessService processService;

    @Test
    public void start() {
        String start = processEngine.start("Process_1");
        System.out.println(start);
    }

    @Test
    public void audit() {
        processEngine.audit("a11ddceec257475ba475a553ffa27cc8", "测试", 4);
    }

    @Test
    public void ognl() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("['demo'] > 1");
        Map<String, Object> params = new HashMap<>();
        params.put("demo", 1);
        Boolean value = exp.getValue(params, Boolean.class);
        System.out.println(value);
    }


}
*/
