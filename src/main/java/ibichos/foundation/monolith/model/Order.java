package ibichos.foundation.monolith.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Order {
    private UUID id;
    private Customer customer;
    private Timestamp checkout_hour;
    private BigDecimal total_price;
}
