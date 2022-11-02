package org.pro.springorder;

import org.pro.springorder.order.Order;
import org.pro.springorder.voucher.Voucher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackageClasses = {Order.class, Voucher.class})
//@ComponentScan(
//        basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher"},
//        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MemoryVoucherRepository.class)})
@ComponentScan(basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher", "org.pro.springorder.configuration"})
public class AppConfiguration {

}
