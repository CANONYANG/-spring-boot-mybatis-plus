package com.njbandou.web.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author: CANONYANG
 * Date: 2018/11/21
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.njbandou.web.mapper*")
public class MybatisPlusConfig {

    /**
     *  mybatis-plus分页插件,自动识别数据库类型 多租户.
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}