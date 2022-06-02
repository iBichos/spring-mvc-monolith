package ibichos.foundation.monolith.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Customer extends Account {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String cpf;
}
