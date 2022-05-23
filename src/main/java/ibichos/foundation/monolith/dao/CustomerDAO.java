package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerDAO {
    private static final String INSERT_CUSTOMER = "INSERT INTO customers (" +
            "name," +
            "email," +
            "cpf," +
            "phone," +
            "password," +
            "address" +
            ") VALUES (" +
            ":name," +
            ":email," +
            ":cpf," +
            ":phone," +
            ":password," +
            ":address" +
            ")";

    private static final String SELECT_CUSTOMER_BY_EMAIL = "SELECT " +
            "c.customer_id, c.name, c.email, c.password, c.cpf, c.phone, c.address " +
            "FROM customers c WHERE c.email=:email";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Customer insert(Customer customer) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("name", customer.getName());
        params.put("email", customer.getEmail());
        params.put("cpf", customer.getCpf());
        params.put("phone", customer.getPhone());
        params.put("password", customer.getPassword());
        params.put("address", customer.getAddress());

        try {
            if (namedParameterJdbcTemplate.update(INSERT_CUSTOMER, params) == 0) {
                throw new Exception("Customer entity was not inserted");
            }
            return customer;
        } catch (DataAccessException dae) {
            throw new Exception("Failed to insert Customer", dae);
        }
    }

    public Optional<Customer> selectByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);

        return namedParameterJdbcTemplate.query(SELECT_CUSTOMER_BY_EMAIL, params, CustomerRowMapper.getInstance()).stream().findFirst();
    }


}
