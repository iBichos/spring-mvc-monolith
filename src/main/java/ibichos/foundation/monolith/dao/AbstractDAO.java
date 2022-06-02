package ibichos.foundation.monolith.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractDAO {
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AbstractDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
