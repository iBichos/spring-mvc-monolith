package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.mapper.ProductsMapper;
import ibichos.foundation.monolith.model.Product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class ProductsDAO extends AbstractDAO {

    private static final String INSERT = "/* ProductsDAO */ " +
            "INSERT INTO products (product_id,merchant_id,name,description,price,amount_in_stock) " +
            "VALUES (:product_id::uuid,:merchant_id::uuid,:name,:description,:price::decimal ,:amount_in_stock::integer ) ";

    private static final String UPDATE = "/* ProductsDAO */ " +
            "UPDATE products " +
            "SET name = :name,description = :description, price = :price::decimal , amount_in_stock = :amount_in_stock::integer " +
            "WHERE product_id::varchar = :product_id ";
    private static final String DELETE = "/* ProductsDAO */ " +
            "DELETE FROM products p " +
            "WHERE p.product_id::varchar = :product_id ";

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
            "WHERE p.product_id IN (:products_ids) ";

    private static final String SELECT_PRODUCTS_BY_MERCHANT_ID = "/* ProductsDAO */ " +
            "SELECT p.product_id, p.merchant_id, p.name, p.description, p.monetary_unit, p.price, p.amount_in_stock " +
            "FROM products p " +
            "WHERE p.merchant_id = :merchant_id ";

    public ProductsDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public List<Product> select() {
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS, ProductsMapper.getInstance());
    }

    public Optional<Product> selectById(UUID productId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("product_id", productId);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCT_BY_PRODUCT_ID, params, ProductsMapper.getInstance()).stream().findFirst();
    }

    public List<Product> selectByMerchantId(UUID merchantId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("merchant_id", merchantId);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_MERCHANT_ID, params, ProductsMapper.getInstance());
    }

    public List<Product> selectByIds(List<UUID> productsIds) {
        Map<String, List<UUID>> params = new HashMap<>();
        params.put("products_ids", productsIds);
        return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_PRODUCTS_IDS, params, ProductsMapper.getInstance());
    }

    public void insert(Product product) {
        Map<String, String> params = new HashMap<>();
        params.put("product_id", product.getProductId().toString());
        params.put("merchant_id", product.getMerchantId().toString());
        params.put("name", product.getName());
        params.put("description", product.getDescription());
        params.put("price", product.getPrice().toString());
        params.put("amount_in_stock", product.getAmountInStock().toString());

        try {
            if (namedParameterJdbcTemplate.update(INSERT, params) == 0) {
                log.info("Product entity was not inserted");
            }
        } catch (DataAccessException dae) {
            log.warn("Failed to insert Product", dae);
        } catch (Exception e) {
            log.warn("Producct entity was not inserted", e);
        }
    }

    public void update(Product product) {
        Map<String, String> params = new HashMap<>();
        params.put("product_id", product.getProductId().toString());
        params.put("merchant_id", product.getMerchantId().toString());
        params.put("name", product.getName());
        params.put("description", product.getDescription());
        params.put("price", product.getPrice().toString());
        params.put("amount_in_stock", product.getAmountInStock().toString());

        namedParameterJdbcTemplate.update(UPDATE, params);
    }

    public void delete(Product product) {
        Map<String, String> params = new HashMap<>();
        params.put("product_id", product.getProductId().toString());
        namedParameterJdbcTemplate.update(DELETE, params);
    }
}
