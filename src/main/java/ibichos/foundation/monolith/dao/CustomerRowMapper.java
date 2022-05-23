package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomerRowMapper implements RowMapper<Customer> {

    private static final CustomerRowMapper instance = new CustomerRowMapper();

    private CustomerRowMapper() {}

    public static CustomerRowMapper getInstance() {
        return instance;
    }

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Customer.builder()
                .id(UUID.fromString(rs.getString("customer_id")))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .cpf(rs.getString("cpf"))
                .phone(rs.getString("phone"))
                .password(rs.getString("password"))
                .address(rs.getString("address"))
                .build();
    }
}
