package ibichos.foundation.monolith.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductCategory {
    private UUID productId;
    private UUID categoryId;
}
