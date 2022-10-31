package org.pro.springorder;

import java.util.UUID;
import java.util.List;

public class Order {

    private final UUID orderId;
    private final UUID customerId;
    private final List<OrderItem> orderItems;
    private Voucher voucher;
    private OrderStatus orderStatus = OrderStatus.ACCEPTED;

    public Order(UUID orderId, UUID customerId, List<OrderItem> orderItems, Voucher voucher) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.voucher = voucher;
    }

    public long totalAmount() {
        Long beforeDiscount = orderItems.stream()
                .map(orderItem -> orderItem.getProductPrice() * orderItem.getQuantity())
                .reduce(0L, Long::sum);
        return voucher.discount(beforeDiscount);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
