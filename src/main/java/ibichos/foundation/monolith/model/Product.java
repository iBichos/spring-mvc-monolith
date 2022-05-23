package ibichos.foundation.monolith.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {
    private UUID id;
    private String name;
    private String description;
    private String monetaryUnit;
    private BigDecimal price;
    private Integer amountInStock;
}
