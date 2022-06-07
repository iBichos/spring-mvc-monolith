package ibichos.foundation.monolith.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderLine {
    private UUID orderId;
    private UUID productId;
    private Integer amount;
}
