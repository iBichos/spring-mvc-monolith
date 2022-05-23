package ibichos.foundation.monolith.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {
    private UUID id;
    private String socialName;
    private String ownerName;
    private String email;
    private String password;
    private String cnpj;
    private String phone;
    private String address;
}
