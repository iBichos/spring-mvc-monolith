package ibichos.foundation.monolith.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class OrderLinesRowMapper implements RowMapper<Map<UUID, Integer>> {
    private static final OrderLinesRowMapper instance = new OrderLinesRowMapper();

    private OrderLinesRowMapper(){}

    public static OrderLinesRowMapper getInstance(){
        return instance;
    }

    @Override
    public Map<UUID, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Collections.singletonMap(UUID.fromString(rs.getString("product_id")), rs.getInt("amount"));
    }
}
