package org.pro.springorder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pro.springorder.order.OrderItem;
import org.pro.springorder.order.OrderService;
import org.pro.springorder.order.OrderStatus;
import org.pro.springorder.voucher.FixedAmountVoucher;
import org.pro.springorder.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringJUnitConfig
@ActiveProfiles("test")
public class MySpringContextTests {

    @Configuration
    @ComponentScan(
            basePackages = {"org.pro.springorder.order", "org.pro.springorder.voucher"}
    )
    static class Config {
    }

    @Autowired
    ApplicationContext context;

    @Autowired
    OrderService orderService;

    @Autowired
    VoucherRepository voucherRepository;

    @Test
    @DisplayName("applicationContext가 생성 되야한다.")
    void testApplicationContext() {
        assertThat(context, notNullValue());
    }

    @Test
    @DisplayName("VoucherRepository가 빈으로 등록되어 있어야 한다.")
    void testVoucherRepositoryCreation() {
        assertThat(context, notNullValue());
        var voucherRepository = context.getBean(VoucherRepository.class);
        assertThat(voucherRepository, notNullValue());
    }

    @Test
    @DisplayName("OrderService 사용해서 주문을 생성할 수 있다.")
    void testOrderService() {
        // given
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
        voucherRepository.insert(fixedAmountVoucher);

        // when
        var order = orderService.createOrder(
                UUID.randomUUID(),
                List.of(new OrderItem(UUID.randomUUID(), 200, 1)),
                fixedAmountVoucher.getVoucherId());

        // then
        assertThat(order.totalAmount(), is(100L));
        assertThat(order.getVoucher().isEmpty(), is(false));
        assertThat(order.getVoucher().get().getVoucherId(), is(fixedAmountVoucher.getVoucherId()));
        assertThat(order.getOrderStatus(), is(OrderStatus.ACCEPTED));
    }
}
