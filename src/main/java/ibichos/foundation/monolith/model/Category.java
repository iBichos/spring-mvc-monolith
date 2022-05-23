package ibichos.foundation.monolith.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Category {
    private UUID id;
    private String name;
    private String description;
}
