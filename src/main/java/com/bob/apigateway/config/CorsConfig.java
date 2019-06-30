package com.bob.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @Auther: toudaizhi
 * @Date: 2019-06-04 22:39
 * @Description: 配置Zuul解决跨域
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        //设置允许跨域
        config.setAllowCredentials(true);
        //原始域http://www.a.com
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        //提交方法post，get
        config.setAllowedMethods(Arrays.asList("*"));
        //时间内，相同的跨域问题，不用再次检查
        config.setMaxAge(300l);

        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
}
