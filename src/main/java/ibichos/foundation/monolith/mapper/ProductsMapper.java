package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductsMapper implements RowMapper<Product> {

    private static final ProductsMapper instance = new ProductsMapper();

    private ProductsMapper() {}

    public static ProductsMapper getInstance() {
        return instance;
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product
                .builder()
                .productId(UUID.fromString(rs.getString("product_id")))
                .merchantId(UUID.fromString(rs.getString("merchant_id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .monetaryUnit(rs.getString("monetary_unit"))
                .price(new BigDecimal(rs.getString("price")))
                .amountInStock(rs.getInt("amount_in_stock"))
                .build();
    }
}
