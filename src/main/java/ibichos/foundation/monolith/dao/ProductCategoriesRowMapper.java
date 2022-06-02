package ibichos.foundation.monolith.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductCategoriesRowMapper implements RowMapper<Map<UUID, UUID>> {

    private static final ProductCategoriesRowMapper instance = new ProductCategoriesRowMapper();

    private ProductCategoriesRowMapper() {}

    public static ProductCategoriesRowMapper getInstance() {
        return instance;
    }

    @Override
    public Map<UUID, UUID> mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Collections.singletonMap(UUID.fromString(rs.getString("product_id")), UUID.fromString(rs.getString("category_id")));
    }
}