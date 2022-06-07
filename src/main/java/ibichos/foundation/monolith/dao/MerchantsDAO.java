package ibichos.foundation.monolith.dao;

import ibichos.foundation.monolith.mapper.MerchantsMapper;
import ibichos.foundation.monolith.model.Merchant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class MerchantsDAO extends AbstractDAO {

    private static final String INSERT_MERCHANT = "/* MerchantsDAO */ " +
            "INSERT INTO merchants (social_name, owner_name, email, cnpj, phone, password, address) " +
            "VALUES (:social_name, :owner_name, :email, :cnpj, :phone, :password, :address)";

    private static final String SELECT_MERCHANT_BY_EMAIL = "/* MerchantsDAO */ " +
            "SELECT m.merchant_id, m.social_name, m.owner_name, m.email, m.password, m.cnpj, m.phone, m.address " +
            "FROM merchants m " +
            "WHERE m.email=:email";

    private static final String SELECT_MERCHANT_BY_EMAIL_AND_PASSWORD = "/* MerchantsDAO */ " +
            "SELECT m.merchant_id, m.social_name, m.owner_name, m.email, m.password, m.cnpj, m.phone, m.address " +
            "FROM merchants m " +
            "WHERE m.email=:email AND m.password=:password";

    private static final String SELECT_MERCHANT_BY_ID = "/* MerchantsDAO */ " +
            "SELECT m.merchant_id, m.social_name, m.owner_name, m.email, m.password, m.cnpj, m.phone, m.address " +
            "FROM merchants m " +
            "WHERE m.merchant_id=:merchant_id";

    private static final String UPDATE = "/* MerchantsDAO */ " +
            "UPDATE merchants " +
            "SET social_name = :social_name, owner_name = :owner_name, cnpj = :cnpj, phone = :phone, address = :address " +
            "WHERE merchant_id::varchar = :merchant_id AND email = :email ";

    public MerchantsDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public Merchant insert(Merchant merchant) {
        Map<String, String> params = new HashMap<>();
        params.put("social_name", merchant.getSocialName());
        params.put("owner_name", merchant.getOwnerName());
        params.put("email", merchant.getEmail());
        params.put("cnpj", merchant.getCnpj());
        params.put("phone", merchant.getPhone());
        params.put("password", merchant.getPassword());
        params.put("address", merchant.getAddress());

        try {
            if (namedParameterJdbcTemplate.update(INSERT_MERCHANT, params) == 0) {
                log.info("Merchant entity was not inserted");
            }
        } catch (DataAccessException dae) {
            log.warn("Failed to insert Merchant", dae);
        } catch (Exception e) {
            log.warn("Merchant entity was not inserted", e);
        }
        return merchant;
    }

    public Optional<Merchant> selectByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);

        return namedParameterJdbcTemplate.query(SELECT_MERCHANT_BY_EMAIL, params, MerchantsMapper.getInstance()).stream().findFirst();
    }

    public Optional<Merchant> selectByEmailAndPassword(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        return namedParameterJdbcTemplate.query(SELECT_MERCHANT_BY_EMAIL_AND_PASSWORD, params, MerchantsMapper.getInstance()).stream().findFirst();
    }

    public Optional<Merchant> selectById(UUID merchantId) {
        Map<String, UUID> params = new HashMap<>();
        params.put("merchant_id", merchantId);

        return namedParameterJdbcTemplate.query(SELECT_MERCHANT_BY_ID, params, MerchantsMapper.getInstance()).stream().findFirst();
    }

    public void update(Merchant merchant) {
        Map<String, String> params = new HashMap<>();
        params.put("merchant_id", merchant.getMerchantId().toString());
        params.put("social_name", merchant.getSocialName());
        params.put("owner_name", merchant.getOwnerName());
        params.put("email", merchant.getEmail());
        params.put("cnpj", merchant.getCnpj());
        params.put("phone", merchant.getPhone());
        params.put("address", merchant.getAddress());

        namedParameterJdbcTemplate.update(UPDATE, params);
    }
}
