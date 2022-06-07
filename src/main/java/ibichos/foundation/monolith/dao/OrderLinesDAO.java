package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.mapper.OrderLinesMapper;
import ibichos.foundation.monolith.model.OrderLine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class OrderLinesDAO extends AbstractDAO {
    private static final String SELECT_BY_ORDER_ID = "/* OrderLinesDAO */ " +
            "SELECT ol.order_id AS order_id, ol.product_id AS product_id, ol.amount AS amount " +
            "FROM order_lines ol " +
            "WHERE ol.order_id = :order_id ";

    private static final String INSERT = "/* OrderLinesDAO */ " +
            "INSERT INTO order_lines (order_id, product_id, amount) " +
            "VALUES (:order_id::uuid, :product_id::uuid, :amount::integer)";

    public OrderLinesDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public List<OrderLine> selectByOrderId(UUID orderId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("order_id", orderId);
        return namedParameterJdbcTemplate.query(SELECT_BY_ORDER_ID, params, OrderLinesMapper.getInstance());
    }

    public OrderLine insert(OrderLine orderLine) {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderLine.getOrderId().toString());
        params.put("product_id", orderLine.getProductId().toString());
        params.put("amount", orderLine.getAmount().toString());
        try {
            if (namedParameterJdbcTemplate.update(INSERT, params) == 0) {
                log.info("OrderLine entity was not inserted");
            }
        } catch (DataAccessException dae) {
            log.warn("Failed to insert OrderLine", dae);
        } catch (Exception e) {
            log.warn("OrderLine entity was not inserted", e);
        }
        return orderLine;
    }
}