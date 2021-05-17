package cn.org.rookie.jeesdp.jeesdp.workflow.definitions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTask extends AbstractModel {

    protected List<String> incomings;
    protected List<String> outgoings;

}
