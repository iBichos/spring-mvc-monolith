package ibichos.foundation.monolith.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    private String address;
}
