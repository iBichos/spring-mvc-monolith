package ibichos.foundation.monolith.model;

import lombok.*;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class Cart {
    public List<ProductAmount> productAmounts = new ArrayList<>();
    private Integer totalAmount = 0;
    private BigDecimal totalPrice = new BigDecimal(0);
    private Boolean isEmpty = true;
}
