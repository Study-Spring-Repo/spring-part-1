package org.pro.springorder;

import org.pro.springorder.order.OrderItem;
import org.pro.springorder.order.OrderProperties;
import org.pro.springorder.order.OrderService;
import org.pro.springorder.voucher.FixedAmountVoucher;
import org.pro.springorder.voucher.JdbcVoucherRepository;
import org.pro.springorder.voucher.VoucherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {

    public static void main(String[] args) {
        var springApplication = new SpringApplication(ProApplication.class);
//        springApplication.setAdditionalProfiles("local");
        var applicationContext = springApplication.run(args);
//        var applicationContext = new AnnotationConfigApplicationContext();

        var orderProperties = applicationContext.getBean(OrderProperties.class);
        System.out.println(MessageFormat.format("version = {0}", orderProperties.getVersion()));
        System.out.println(MessageFormat.format("minimumOrderAmount = {0}", orderProperties.getMinimumOrderAmount()));
        System.out.println(MessageFormat.format("supportVendors = {0}", orderProperties.getSupportVendors()));
        System.out.println(MessageFormat.format("description = {0}", orderProperties.getDescription()));

        var customerId = UUID.randomUUID();
        var voucherRepository = applicationContext.getBean(VoucherRepository.class);
        var voucher = voucherRepository.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));

        System.out.println(MessageFormat.format("is Jdbc Repo -> {0}", voucherRepository instanceof JdbcVoucherRepository));
        System.out.println(MessageFormat.format("is Jdbc Repo -> {0}", voucherRepository.getClass().getCanonicalName()));

        var orderService = applicationContext.getBean(OrderService.class);
        var order = orderService.createOrder(customerId, new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }}, voucher.getVoucherId());
        Assert.isTrue(
                order.totalAmount() == 90L,
                MessageFormat.format("totalAmount {0} is not 90", order.totalAmount()));

        applicationContext.close();
    }
}
