package cn.wenhe9.template.config;

import cn.wenhe9.template.config.cors.CorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 跨域配置类
 * @author: DuJinliang
 * @create: 2023/11/5
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    private final CorsProperties corsProperties;

    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(corsProperties.isAllowCredentials())
                .allowedOriginPatterns(corsProperties.getAllowedOrigins().toArray(new String[0]))
                .allowedMethods(corsProperties.getAllowedMethods().split(","))
                .allowedHeaders(corsProperties.getAllowedHeaders().split(","))
                .maxAge(corsProperties.getMaxAge());
    }
}
