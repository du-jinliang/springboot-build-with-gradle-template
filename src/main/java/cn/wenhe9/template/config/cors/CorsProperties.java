package cn.wenhe9.template.config.cors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 跨域配置属性类 从配置文件中读取数据
 * @author: DuJinliang
 * @create: 2023/11/5
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    /**
     * 允许的协议+地址+端口
     */
    private List<String> allowedOrigins;
    /**
     * 允许的方法类型
     */
    private String allowedMethods;
    /**
     * 允许的请求头
     */
    private String allowedHeaders;
    /**
     * 是否允许携带身份凭证 如 cookies http认证信息
     */
    private boolean allowCredentials;
    /**
     * 预检请求缓存时间
     */
    private long maxAge;
}
