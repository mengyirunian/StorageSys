package com.mengyirunian.config;

import org.springframework.context.annotation.*;

/**
 *  应用上下文配置
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@Import({StorageDataConfig.class})
public class RootConfig {



}
