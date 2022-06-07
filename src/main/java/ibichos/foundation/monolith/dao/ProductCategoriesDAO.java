package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.mapper.ProductCategoriesMapper;
import ibichos.foundation.monolith.model.ProductCategory;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductCategoriesDAO extends AbstractDAO {
    private static final String SELECT_BY_PRODUCT_ID = "/* ProductCategoriesDAO */ " +
            "SELECT pc.product_id AS product_id, c.category_id AS category_id " +
            "FROM product_categories pc " +
            "WHERE pc.product_id = :product_id ";

    private static final String SELECT_BY_CATEGORY_ID = "/* ProductCategoriesDAO */ " +
            "SELECT pc.product_id AS product_id, pc.category_id AS category_id " +
            "FROM product_categories pc " +
            "WHERE pc.category_id = :category_id ";

    public ProductCategoriesDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public List<ProductCategory> selectByProductId(UUID productId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("product_id", productId);
        return namedParameterJdbcTemplate.query(SELECT_BY_PRODUCT_ID, params, ProductCategoriesMapper.getInstance());
    }

    public List<ProductCategory> selectByCategoryId(UUID categoryId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("category_id", categoryId);
        return namedParameterJdbcTemplate.query(SELECT_BY_CATEGORY_ID, params, ProductCategoriesMapper.getInstance());
    }
}
