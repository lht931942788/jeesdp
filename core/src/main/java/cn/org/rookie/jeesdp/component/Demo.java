package cn.org.rookie.jeesdp.component;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Element;

/**
 * @author LIHAITAO
 */
@Configuration
public class Demo implements BeanDefinitionParser {

    private static final String RESOURCE_PATTERN = "/**/*.class";

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        System.out.println(this.getClass().getName());
        return null;
    }
}
