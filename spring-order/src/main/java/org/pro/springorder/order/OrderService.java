package org.pro.springorder.order;

import org.pro.springorder.configuration.VersionProvider;
import org.pro.springorder.voucher.VoucherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final VoucherService voucherService;
    private final OrderRepository orderRepository;
    private final VersionProvider versionProvider;

    public OrderService(VoucherService voucherService, OrderRepository orderRepository, VersionProvider versionProvider) {
        this.voucherService = voucherService;
        this.orderRepository = orderRepository;
        this.versionProvider = versionProvider;
    }

    public Order createOrder(UUID customerId, List<OrderItem> orderItems) {
        var order = new Order(UUID.randomUUID(), customerId, orderItems);
        return orderRepository.insert(order);
    }

    public Order createOrder(UUID customerId, List<OrderItem> orderItems, UUID voucherId) {
        versionProvider.getVersion();
        var voucher = voucherService.getVoucher(voucherId);
        var order = new Order(UUID.randomUUID(), customerId, orderItems, voucher);
        orderRepository.insert(order);
        voucherService.useVoucher(voucher);
        return order;
    }

}
