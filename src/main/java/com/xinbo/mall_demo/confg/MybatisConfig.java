package com.xinbo.mall_demo.confg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xinbo.mall_demo.mbg.mapper")
public class MybatisConfig {

}
