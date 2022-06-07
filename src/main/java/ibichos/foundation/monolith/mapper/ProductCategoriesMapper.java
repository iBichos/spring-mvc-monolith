package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.ProductCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductCategoriesMapper implements RowMapper<ProductCategory> {

    private static final ProductCategoriesMapper instance = new ProductCategoriesMapper();

    private ProductCategoriesMapper() {}

    public static ProductCategoriesMapper getInstance() {
        return instance;
    }

    @Override
    public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProductCategory
                .builder()
                .productId(UUID.fromString(rs.getString("product_id")))
                .categoryId(UUID.fromString(rs.getString("category_id")))
                .build();
    }
}