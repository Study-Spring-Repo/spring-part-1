package org.pro.springorder;

import org.pro.springorder.order.Order;
import org.pro.springorder.voucher.MemoryVoucherRepository;
import org.pro.springorder.voucher.Voucher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan(basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher"})
//@ComponentScan(basePackageClasses = {Order.class, Voucher.class})
@ComponentScan(
        basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MemoryVoucherRepository.class)})
public class AppConfiguration {

}
