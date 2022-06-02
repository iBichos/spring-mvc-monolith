package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoriesRowMapper implements RowMapper<Category> {

    private static final CategoriesRowMapper instance = new CategoriesRowMapper();

    private CategoriesRowMapper() {}

    public static CategoriesRowMapper getInstance() {
        return instance;
    }

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .categoryId(UUID.fromString(rs.getString("category_id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build();
    }
}
