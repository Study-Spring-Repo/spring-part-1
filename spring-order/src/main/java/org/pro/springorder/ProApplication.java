package org.pro.springorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher", "org.pro.springorder.configuration"}
)
public class ProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProApplication.class, args);
    }
}
