package cn.org.rookie.jeesdp.jeesdp.workflow.definitions;


import cn.org.rookie.jeesdp.jeesdp.workflow.entity.Task;
import cn.org.rookie.jeesdp.jeesdp.workflow.service.ProcessService;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractModel implements ApplicationContextAware {

    protected static final Log logger = LogFactory.getLog(AbstractModel.class);
    protected String id;
    protected String name;
    protected ProcessService processService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.processService = applicationContext.getBean(ProcessService.class);
    }

    public abstract boolean exec(Task task, Map<String, Object> params);

    public AbstractModel getNextNode(Task task, Map<String, Object> params) {
        List<String> outgoings = ((AbstractTask) task.getCurrentNode()).getOutgoings();
        Process process = task.getProcess();
        if (outgoings.size() == 1) {
            return process.getNode(outgoings.get(0));
        } else {
            List<AbstractFlow> abstractFlows = new ArrayList<>();
            for (String id : outgoings) {
                AbstractFlow abstractFlow = (AbstractFlow) process.getNode(id);
                String expression = abstractFlow.getExpression();
                if (expression != null && !expression.isEmpty()) {
                    ExpressionParser parser = new SpelExpressionParser();
                    Expression exp = parser.parseExpression(expression);
                    Boolean result = exp.getValue(params, Boolean.class);
                    if (result != null && result) {
                        abstractFlows.add(abstractFlow);
                    }
                }
            }
            if (abstractFlows.size() == 1) {
                AbstractFlow abstractFlow = abstractFlows.get(0);
                processService.insertLog(task.getId(), this, abstractFlow.getName(), 2, false);
                return abstractFlow;
            }
        }
        return null;
    }

}
