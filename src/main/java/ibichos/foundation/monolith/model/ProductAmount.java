package ibichos.foundation.monolith.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductAmount {
    private Product product;
    private Integer amount;
}
