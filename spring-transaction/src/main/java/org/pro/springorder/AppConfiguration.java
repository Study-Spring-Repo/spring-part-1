package org.pro.springorder;

import org.pro.springorder.configuration.YamlPropertiesFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(
        basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher", "org.pro.springorder.configuration", "org.pro.springorder.aop"}
)
@PropertySource(value = "/application.yaml", factory = YamlPropertiesFactory.class)
@EnableConfigurationProperties
public class AppConfiguration {
}