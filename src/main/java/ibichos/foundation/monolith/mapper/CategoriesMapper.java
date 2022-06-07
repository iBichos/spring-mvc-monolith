package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.Category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoriesMapper implements RowMapper<Category> {

    private static final CategoriesMapper instance = new CategoriesMapper();

    private CategoriesMapper() {}

    public static CategoriesMapper getInstance() {
        return instance;
    }

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category
                .builder()
                .categoryId(UUID.fromString(rs.getString("category_id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build();
    }
}
