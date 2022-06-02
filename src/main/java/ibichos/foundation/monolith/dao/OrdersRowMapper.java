package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OrdersRowMapper implements RowMapper<Order> {
    private static final OrdersRowMapper instance = new OrdersRowMapper();

    private OrdersRowMapper(){}

    public static OrdersRowMapper getInstance(){
        return instance;
    }

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .orderId(UUID.fromString(rs.getString("order_id")))
                .customerId(UUID.fromString(rs.getString("customer_id")))
                .checkoutHour(rs.getTimestamp("checkout_hour"))
                .build();
    }
}
