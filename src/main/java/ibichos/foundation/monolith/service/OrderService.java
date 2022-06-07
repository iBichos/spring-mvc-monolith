package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.OrderLinesDAO;
import ibichos.foundation.monolith.dao.OrdersDAO;
import ibichos.foundation.monolith.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private OrderLinesDAO orderLinesDAO;

    public Order newOrder(Cart cart, Customer customer) {
        Order order = Order
                .builder()
                .orderId(UUID.randomUUID())
                .customerId(customer
                .getCustomerId()).checkoutHour(new Timestamp(System.currentTimeMillis()))
                .build();

        ordersDAO.insert(order);

        cart.getProductAmounts().forEach(productAmount -> {
            OrderLine orderLine = OrderLine
                    .builder()
                    .orderId(order.getOrderId())
                    .productId(productAmount.getProduct().getProductId())
                    .amount(productAmount.getAmount())
                    .build();

            orderLinesDAO.insert(orderLine);
        });

        return order;
    }

    public List<Order> orders(UUID orderId) {
        return ordersDAO.select(orderId);
    }
}
