package nl._42.spring.script_hooks;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class SpringScriptHooksDependencyPostProcessor implements BeanFactoryPostProcessor {

    private final String dependOnBean;

    public SpringScriptHooksDependencyPostProcessor(final String dependOnBean) {
        this.dependOnBean = dependOnBean;
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Arrays.stream(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, SpringLiquibase.class))
                .map(beanFactory::getBeanDefinition)
                .forEach(beanDefinition -> {
                    String[] dependsOn = beanDefinition.getDependsOn();
                    beanDefinition.setDependsOn(StringUtils.addStringToArray(dependsOn, dependOnBean));
                });
    }

}
