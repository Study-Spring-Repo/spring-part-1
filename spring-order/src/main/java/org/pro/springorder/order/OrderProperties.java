package org.pro.springorder.order;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
public class OrderProperties implements InitializingBean {

    @Value("${example.version:v0.0.0}")
    private String version;

    @Value("${example.minimum-order-amount}")
    private String minimumOrderAmount;

    @Value("${example.support-vendors}") // 타입 변화 가능한 것만 가능
    private List<String> supportVendors;

//    @Value("${JAVA_HOME}")
//    private String javaHome;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(MessageFormat.format("[OrderProperties] version = {0}", version));
        System.out.println(MessageFormat.format("[OrderProperties] minimumOrderAmount = {0}", minimumOrderAmount));
        System.out.println(MessageFormat.format("[OrderProperties] supportVendors = {0}", supportVendors));
//        System.out.println(MessageFormat.format("[OrderProperties] javaHome = {0}", javaHome));
    }
}
