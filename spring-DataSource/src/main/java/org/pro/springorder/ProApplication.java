package org.pro.springorder;

import org.pro.springorder.order.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProApplication.class);

    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(ProApplication.class, args);
//        applicationContext.getEnvironment().addActiveProfile("local");
        var orderProperties = applicationContext.getBean(OrderProperties.class);
        logger.warn("logger name => {}" + logger.getName());
        logger.warn("version = {}", orderProperties.getVersion());
        logger.warn("minimumOrderAmount = {}", orderProperties.getMinimumOrderAmount());
        logger.warn("supportVendors = {}", orderProperties.getSupportVendors());
        logger.warn("description = {}", orderProperties.getDescription());
    }
}
