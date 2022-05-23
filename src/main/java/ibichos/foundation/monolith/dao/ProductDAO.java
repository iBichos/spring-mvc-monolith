package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductDAO {

    private static final String SELECT_PRODUCTS = "SELECT " +
            "p.product_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock " +
            "FROM products p WHERE 1 = 1";

    private static final String SELECT_PRODUCTS_BY_ID = "SELECT " +
            "p.product_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock " +
            "FROM products p WHERE p.product_id =:id";

    private static final String SELECT_PRODUCTS_BY_CATEGORY_NAME = "SELECT p.product_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock, c.name FROM products p\n" +
            "INNER JOIN product_categories pc ON p.product_id = pc.product_id \n" +
            "INNER JOIN categories c ON c.category_id = pc.category_id\n" +
            "WHERE c.name=:category";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> select() {
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS, ProductRowMapper.getInstance());
    }

    public List<Product> select(String category) {
        Map<String, String> params = new HashMap<>();
        params.put("category", category);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_CATEGORY_NAME, params, ProductRowMapper.getInstance());
    }

    public Product selectById(UUID id) {
        Map<String, UUID> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_ID, params, ProductRowMapper.getInstance()).stream().findFirst().get();
    }
}
