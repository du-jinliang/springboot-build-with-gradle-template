package cn.wenhe9.template.utils.spring;

/**
 * @description: spring工具接口 用于代替 applicationContext 获取 spring 中的一些bean或配置信息
 * @author: DuJinliang
 * @create: 2023/3/16
 */
public interface SpringUtil {

    <T> T getBean(Class<T> beanClass, Class<?>... generics);

    <T> T getBean(Class<T> beanClass);

    <T> T getBean(String beanName, Class<T> beanClass);

    String getApplicationName();
}
