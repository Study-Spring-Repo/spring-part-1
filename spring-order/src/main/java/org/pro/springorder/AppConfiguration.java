package org.pro.springorder;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(
        basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher", "org.pro.springorder.configuration"}
)
@PropertySource("application.properties")
public class AppConfiguration {
}