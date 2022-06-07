package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.mapper.OrdersMapper;
import ibichos.foundation.monolith.model.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class OrdersDAO extends AbstractDAO {
    private static final String SELECT_ORDERS_BY_CUSTOMER_ID = "/* OrdersDAO */ " +
            "SELECT o.order_id, o.customer_id, o.checkout_hour " +
            "FROM orders o " +
            "WHERE o.customer_id = :customer_id ";

    private static final String INSERT = "/* OrdersDAO */ " +
            "INSERT INTO orders (order_id, customer_id, checkout_hour) " +
            "VALUES (:order_id::uuid, :customer_id::uuid, :checkout_hour::timestamp)";
    public OrdersDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public List<Order> select(UUID orderId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("customer_id", orderId);
        return namedParameterJdbcTemplate.query(SELECT_ORDERS_BY_CUSTOMER_ID, params, OrdersMapper.getInstance());
    }

    public Order insert(Order order) {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", order.getOrderId().toString());
        params.put("customer_id", order.getCustomerId().toString());
        params.put("checkout_hour", order.getCheckoutHour().toString());

        try {
            if (namedParameterJdbcTemplate.update(INSERT, params) == 0) {
                log.info("Order entity was not inserted");
            }
        } catch (DataAccessException dae) {
            log.warn("Failed to insert Order", dae);
        } catch (Exception e) {
            log.warn("Order entity was not inserted", e);
        }
        return order;
    }

}
