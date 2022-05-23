package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MerchantDAO {
    private static final String SELECT_MERCHANT_BY_EMAIL = "SELECT " +
            "m.merchant_id, m.socialName, m.ownerName, m.email, m.password, m.cnpj, m.phone, m.address " +
            "FROM merchants m WHERE m.email=:email";
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Merchant> selectByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);

        return namedParameterJdbcTemplate.query(SELECT_MERCHANT_BY_EMAIL, params, MerchantRowMapper.getInstance()).stream().findFirst();
    }

}
