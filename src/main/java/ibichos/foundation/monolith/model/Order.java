package ibichos.foundation.monolith.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Order {
    private UUID orderId;
    private UUID customerId;
    private Timestamp checkoutHour;
}
