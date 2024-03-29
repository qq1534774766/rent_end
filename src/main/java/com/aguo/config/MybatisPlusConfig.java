package com.aguo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: aguo
 * @DateTime: 2022/4/24 17:28
 * @Description: TODO
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页功能
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> {
//            // 注册类型处理器
//            configuration.getTypeHandlerRegistry().register(Boolean.class, new BooleanTypeHandler());
//        };
//    }
}
