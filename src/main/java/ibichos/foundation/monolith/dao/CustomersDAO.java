package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class CustomersDAO extends AbstractDAO {
    private static final String INSERT_CUSTOMER = "/* CustomersDAO */ " +
            "INSERT INTO customers (first_name, last_name, email, cpf, phone, password, address) " +
            "VALUES (:first_name, :last_name, :email, :cpf, :phone, :password, :address)";

    private static final String SELECT_CUSTOMER_BY_EMAIL = "/* CustomersDAO */ " +
            "SELECT c.customer_id, c.first_name, c.last_name, c.email, c.password, c.cpf, c.phone, c.address " +
            "FROM customers c " +
            "WHERE c.email=:email ";

    private static final String SELECT_CUSTOMER_BY_EMAIL_AND_PASSWORD = "/* CustomersDAO */ " +
            "SELECT c.customer_id, c.first_name, c.last_name, c.email, c.password, c.cpf, c.phone, c.address " +
            "FROM customers c " +
            "WHERE c.email=:email AND c.password=:password ";

    public CustomersDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public Customer insert(Customer customer) {
        Map<String, String> params = new HashMap<>();
        params.put("first_name", customer.getFirstName());
        params.put("last_name", customer.getLastName());
        params.put("email", customer.getEmail());
        params.put("cpf", customer.getCpf());
        params.put("phone", customer.getPhone());
        params.put("password", customer.getPassword());
        params.put("address", customer.getAddress());

        try {
            if (namedParameterJdbcTemplate.update(INSERT_CUSTOMER, params) == 0) {
                log.info("Customer entity was not inserted");
            }
        } catch (DataAccessException dae) {
            log.warn("Failed to insert Customer", dae);
        } catch (Exception e) {
            log.warn("Customer entity was not inserted", e);
        }
        return customer;
    }

    public Optional<Customer> selectByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        return namedParameterJdbcTemplate.query(SELECT_CUSTOMER_BY_EMAIL, params, CustomersRowMapper.getInstance()).stream().findFirst();
    }

    public Optional<Customer> selectByEmailAndPassword(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        return namedParameterJdbcTemplate.query(SELECT_CUSTOMER_BY_EMAIL_AND_PASSWORD, params, CustomersRowMapper.getInstance()).stream().findFirst();
    }
}
