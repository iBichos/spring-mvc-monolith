package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.model.Cart;
import ibichos.foundation.monolith.model.Product;
import ibichos.foundation.monolith.model.ProductAmount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@SessionScope
public class CartService {

    @Autowired
    private Cart cart;

    public void add(Product product) {
        if (cart.getProductAmounts()
                .stream()
                .anyMatch(productAmount -> productAmount.getProduct().getProductId().equals(product.getProductId())))
            increment(product);
        else
            cart.getProductAmounts().add(ProductAmount.builder().product(product).amount(1).build());
    }

    public void increment(Product product) {
        cart.getProductAmounts()
                .stream()
                .filter(productAmount -> productAmount.getProduct().getProductId().equals(product.getProductId()))
                .findAny()
                .ifPresent(productAmount -> {
                    if (productAmount.getAmount() <= productAmount.getProduct().getAmountInStock())
                        productAmount.setAmount(productAmount.getAmount() + 1);
                });
    }

    public void decrement(Product product) {
        cart.getProductAmounts()
                .stream()
                .filter(productAmount -> productAmount.getProduct().getProductId().equals(product.getProductId()))
                .findAny()
                .ifPresent(productAmount -> {
                    if (productAmount.getAmount() > 0)
                        productAmount.setAmount(productAmount.getAmount() - 1);
                });
    }

    public void remove(Product product) {
        cart.getProductAmounts()
                .removeIf(productAmount -> productAmount.getProduct().getProductId().equals(product.getProductId()));
    }

    public void discard() {
        cart = new Cart();
    }

    public List<Integer> amounts() {
        return cart.getProductAmounts()
                .stream()
                .map(ProductAmount::getAmount)
                .collect(Collectors.toList());
    }

    public List<ProductAmount> productAmounts() {
        return cart.getProductAmounts();
    }

    public Cart status() {
        cart.setTotalAmount(amounts().stream().filter(Objects::nonNull).reduce(Integer::sum).orElse(0));
        cart.setTotalPrice(productAmounts().stream().map(productAmount -> productAmount.getProduct().getPrice().multiply(BigDecimal.valueOf(productAmount.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        cart.setIsEmpty(cart.getProductAmounts().isEmpty());
        return cart;
    }
}
