package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.Order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OrdersMapper implements RowMapper<Order> {
    private static final OrdersMapper instance = new OrdersMapper();

    private OrdersMapper(){}

    public static OrdersMapper getInstance(){
        return instance;
    }

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order
                .builder()
                .orderId(UUID.fromString(rs.getString("order_id")))
                .customerId(UUID.fromString(rs.getString("customer_id")))
                .checkoutHour(rs.getTimestamp("checkout_hour"))
                .build();
    }
}
