package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Merchant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MerchantRowMapper implements RowMapper<Merchant> {

private static final MerchantRowMapper instance = new MerchantRowMapper();

private MerchantRowMapper(){}

public static MerchantRowMapper getInstance(){
        return instance;
        }

@Override
public Merchant mapRow(ResultSet rs, int rowNum)throws SQLException {
        return Merchant.builder()
        .id(UUID.fromString(rs.getString("merchant_id")))
        .socialName(rs.getString("socialName"))
        .ownerName(rs.getString("ownerName"))
        .email(rs.getString("email"))
        .cnpj(rs.getString("cnpj"))
        .phone(rs.getString("phone"))
        .password(rs.getString("password"))
        .address(rs.getString("address"))
        .build();
        }
}