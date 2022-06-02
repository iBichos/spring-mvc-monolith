package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Product;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductsDAO extends AbstractDAO {

    private static final String SELECT_PRODUCTS = "/* ProductsDAO */ " +
            "SELECT p.product_id, p.merchant_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock " +
            "FROM products p " +
            "WHERE 1 = 1 ";

    private static final String SELECT_PRODUCT_BY_PRODUCT_ID = "/* ProductsDAO */ " +
            "SELECT p.product_id, p.merchant_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock " +
            "FROM products p " +
            "WHERE p.product_id = :product_id ";

    private static final String SELECT_PRODUCTS_BY_PRODUCTS_IDS = "/* ProductsDAO */ " +
            "SELECT p.product_id, p.merchant_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock " +
            "FROM products p " +
            "WHERE p.product_id IN ( :products_ids ) ";

    private static final String AGGREGATE_CATEGORIES_IDS_BY_PRODUCT_ID = "/* ProductsDAO */ " +
            "SELECT p.product_id, pc.category_id " +
            "FROM products p " +
            "INNER JOIN product_categories pc " +
            "ON p.product_id = pc.product_id " +
            "WHERE p.product_id = :product_id ";

    public ProductsDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public List<Product> select() {
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS, ProductsRowMapper.getInstance());
    }

    public Optional<Product> selectById(UUID productId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("product_id", productId);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCT_BY_PRODUCT_ID, params, ProductsRowMapper.getInstance()).stream().findFirst();
    }

    public List<Product> selectByIds(List<UUID> productsIds) {
        Map<String, List<UUID>> params = new HashMap<>();
        params.put("products_ids", productsIds);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_PRODUCTS_IDS, params, ProductsRowMapper.getInstance());
    }

    public List<UUID> aggregateCategoriesIds(UUID productId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("product_id", productId);
        return namedParameterJdbcTemplate.query(AGGREGATE_CATEGORIES_IDS_BY_PRODUCT_ID, params, ProductCategoriesRowMapper.getInstance())
                .stream()
                .map(tuple -> tuple.values().stream().findFirst().orElseThrow())
                .collect(Collectors.toList());
    }
}
