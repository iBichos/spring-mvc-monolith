package ibichos.foundation.monolith.model;

import lombok.*;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Component
@NoArgsConstructor
public class Cart {
    private final Map<UUID, Integer> productsIdsAndAmounts = new HashMap<>();
    private final UUID cartId = UUID.randomUUID();
    private Integer totalAmount = 0;
    private BigDecimal totalPrice = new BigDecimal(0);
    private Boolean isEmpty = true;
}
