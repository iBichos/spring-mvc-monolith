package ibichos.foundation.monolith.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public abstract class Account {
    private String email;
    private String password;
    private String phone;
    private String address;
}
