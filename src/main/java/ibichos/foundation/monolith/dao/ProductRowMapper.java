package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Customer;
import ibichos.foundation.monolith.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductRowMapper implements RowMapper<Product> {

    private static final ProductRowMapper instance = new ProductRowMapper();

    private ProductRowMapper() {}

    public static ProductRowMapper getInstance() {
        return instance;
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(UUID.fromString(rs.getString("product_id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .monetaryUnit(rs.getString("monetary_unit"))
                .price(new BigDecimal(rs.getString("price")))
                .amountInStock(rs.getInt("amount_in_stock"))
                .build();
    }
}
