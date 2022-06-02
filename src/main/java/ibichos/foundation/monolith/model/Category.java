package ibichos.foundation.monolith.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class Category {
    private UUID categoryId;
    private String name;
    private String description;
}
