package ibichos.foundation.monolith.mapper;

import ibichos.foundation.monolith.model.Merchant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MerchantsMapper implements RowMapper<Merchant> {

private static final MerchantsMapper instance = new MerchantsMapper();

private MerchantsMapper(){}

public static MerchantsMapper getInstance(){
        return instance;
        }

@Override
public Merchant mapRow(ResultSet rs, int rowNum)throws SQLException {
        return Merchant
                .builder()
                .merchantId(UUID.fromString(rs.getString("merchant_id")))
                .socialName(rs.getString("social_name"))
                .ownerName(rs.getString("owner_name"))
                .email(rs.getString("email"))
                .cnpj(rs.getString("cnpj"))
                .phone(rs.getString("phone"))
                .password(rs.getString("password"))
                .address(rs.getString("address"))
                .build();
        }
}