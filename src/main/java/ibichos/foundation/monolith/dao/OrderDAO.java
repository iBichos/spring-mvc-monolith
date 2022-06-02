package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Order;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderDAO extends AbstractDAO {

    private static final String SELECT_ORDERS_BY_ORDER_ID = "/* OrdersDAO */ " +
            "SELECT o.order_id, o.customer_id, o.checkout_hour " +
            "FROM orders o " +
            "WHERE o.order_id = :order_id ";

    private static final String AGGREGATE_PRODUCTS_IDS_AND_AMOUNT_BY_ORDER_ID = "/* OrdersDAO */ " +
            "SELECT ol.product_id, ol.amount " +
            "FROM orders o " +
            "INNER JOIN order_lines ol " +
            "ON o.order_id = ol.order_id " +
            "WHERE o.order_id = :order_id ";

    public OrderDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public Optional<Order> selectByOrderId(UUID productId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("order_id", productId);
        return namedParameterJdbcTemplate.query(SELECT_ORDERS_BY_ORDER_ID, params, OrdersRowMapper.getInstance()).stream().findFirst();
    }

    public List<Map<UUID, Integer>> aggregateProductsIdsAndAmountByOrderId(UUID orderId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("order_id", orderId);
        return namedParameterJdbcTemplate.query(AGGREGATE_PRODUCTS_IDS_AND_AMOUNT_BY_ORDER_ID, params, OrderLinesRowMapper.getInstance());
    }
}
