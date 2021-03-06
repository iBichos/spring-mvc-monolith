package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomersMapper implements RowMapper<Customer> {

    private static final CustomersMapper instance = new CustomersMapper();

    private CustomersMapper() {}

    public static CustomersMapper getInstance() {
        return instance;
    }

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Customer
                .builder()
                .customerId(UUID.fromString(rs.getString("customer_id")))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .cpf(rs.getString("cpf"))
                .phone(rs.getString("phone"))
                .password(rs.getString("password"))
                .address(rs.getString("address"))
                .build();
    }
}
