package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Category;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CategoriesDAO extends AbstractDAO{

    private static final String SELECT_CATEGORIES = "/* CategoriesDAO */ " +
            "SELECT c.category_id, c.name, c.description " +
            "FROM categories c " +
            "WHERE 1 = 1 ";

    private static final String SELECT_CATEGORIES_BY_ID = "/* CategoriesDAO */ " +
            "SELECT c.category_id, c.name, c.description " +
            "FROM categories c " +
            "WHERE c.category_id =:category_id ";

    private static final String SELECT_CATEGORIES_BY_NAME = "/* CategoriesDAO */ " +
            "SELECT c.category_id, c.name, c.description " +
            "FROM categories c " +
            "WHERE c.name =:name ";

    private static final String SELECT_CATEGORIES_BY_IDS = "/* CategoriesDAO */ " +
            "SELECT c.category_id, c.name, c.description " +
            "FROM categories c " +
            "WHERE c.category_id IN (:categories_ids) ";

    private static final String AGGREGATE_PRODUCTS_IDS_BY_CATEGORY_ID = "/* ProductsDAO */ " +
            "SELECT c.category_id, pc.product_id " +
            "FROM categories c " +
            "INNER JOIN product_categories pc " +
            "ON c.category_id = pc.category_id " +
            "WHERE c.category_id = :category_id ";

    public CategoriesDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public List<Category> select() {
        return namedParameterJdbcTemplate.query(SELECT_CATEGORIES, CategoriesRowMapper.getInstance());
    }

    public Optional<Category> selectById(UUID categoryId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("category_id", categoryId);
        return namedParameterJdbcTemplate.query(SELECT_CATEGORIES_BY_ID, params, CategoriesRowMapper.getInstance()).stream().findFirst();
    }

    public Optional<Category> selectByName(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        return namedParameterJdbcTemplate.query(SELECT_CATEGORIES_BY_NAME, params, CategoriesRowMapper.getInstance()).stream().findFirst();
    }

    public List<Category> selectByIds(List<UUID> categoriesIds) {
        Map<String, List<UUID>> params = new HashMap<>();
        params.put("categories_ids", categoriesIds);
        return namedParameterJdbcTemplate.query(SELECT_CATEGORIES_BY_IDS, params, CategoriesRowMapper.getInstance());
    }

    public List<UUID> aggregateProductsIds(UUID categoryId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("category_id", categoryId);
        return namedParameterJdbcTemplate.query(AGGREGATE_PRODUCTS_IDS_BY_CATEGORY_ID, params, ProductCategoriesRowMapper.getInstance())
                .stream()
                .map(tuple -> tuple.keySet().stream().findFirst().orElseThrow())
                .collect(Collectors.toList());
    }
}
