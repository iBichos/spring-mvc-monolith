package ibichos.foundation.monolith.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Merchant extends Account {
    private UUID merchantId;
    private String socialName;
    private String ownerName;
    private String cnpj;
}
