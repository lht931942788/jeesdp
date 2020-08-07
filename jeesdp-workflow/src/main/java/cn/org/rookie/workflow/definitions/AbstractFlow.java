package cn.org.rookie.workflow.definitions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractFlow extends AbstractModel {

    protected String sourceRef;
    protected String targetRef;
    protected String expression;
}
