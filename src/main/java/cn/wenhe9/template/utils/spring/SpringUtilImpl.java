package cn.wenhe9.template.utils.spring;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description: spring 工具接口实现类
 * @author: DuJinliang
 * @create: 2023/3/16
 */
@Component
public class SpringUtilImpl implements SpringUtil{

    @Resource
    ApplicationContext applicationContext;

    @Resource
    private Environment environment;

    @Override
    public <T> T getBean(Class<T> beanClass, Class<?>... generics) {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(beanClass, generics);
        String[] beanNames = applicationContext.getBeanNamesForType(resolvableType);
        if (beanNames.length != 1) {
            if (beanNames.length > 1) {
                throw new NoUniqueBeanDefinitionException(beanClass, beanNames.length, "存在不止一个符合条件的bean");
            } else {
                throw new NoSuchBeanDefinitionException("不存在符合条件的bean");
            }
        }
        String beanName = beanNames[0];
        return applicationContext.getBean(beanName, beanClass);
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return applicationContext.getBean(beanName, beanClass);
    }


    @Override
    public String getApplicationName() {
        String property = environment.getProperty("spring.application.name");
        if (Objects.nonNull(property) && !StringUtils.hasText(property)) {
            return property;
        }
        return null;
    }
}
