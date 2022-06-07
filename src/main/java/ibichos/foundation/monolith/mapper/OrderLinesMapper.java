package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.OrderLine;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OrderLinesMapper implements RowMapper<OrderLine> {
    private static final OrderLinesMapper instance = new OrderLinesMapper();

    private OrderLinesMapper(){}

    public static OrderLinesMapper getInstance(){
        return instance;
    }

    @Override
    public OrderLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        return OrderLine
                .builder()
                .orderId(UUID.fromString(rs.getString("order_id")))
                .productId(UUID.fromString(rs.getString("product_id")))
                .amount(rs.getInt("amount"))
                .build();
    }
}
