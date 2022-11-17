package org.pro.springorder.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pro.springorder.voucher.FixedAmountVoucher;
import org.pro.springorder.voucher.VoucherRepository;
import org.pro.springorder.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

@SpringJUnitConfig
@ActiveProfiles("test")
public class AopTests {

    @Configuration
    @ComponentScan(
            basePackages = {"org.pro.springorder.voucher", "org.pro.springorder.aop"}
    )
    @EnableAspectJAutoProxy
    static class Config {
    }

    @Autowired
    ApplicationContext context;

    @Autowired
    VoucherRepository voucherRepository;

    @Test
    @DisplayName("Aop test")
    public void testOrderService() {
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
        voucherRepository.insert(fixedAmountVoucher);
    }
}
