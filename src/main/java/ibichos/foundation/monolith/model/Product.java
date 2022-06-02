package ibichos.foundation.monolith.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Builder
@Getter
@Setter
public class Product {
    private UUID productId;
    private UUID merchantId;
    private String name;
    private String description;
    private String monetaryUnit;
    private BigDecimal price;
    private Integer amountInStock;
}
