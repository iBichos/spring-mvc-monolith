package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@SessionScope
public class CartService {

    @Autowired
    private Cart cart;

    public Cart addProduct(UUID productId, Integer amountInStock) {
        cart.getProductsIdsAndAmounts().putIfAbsent(productId, 0);
        return incrementAmount(productId, amountInStock);
    }

    public Cart incrementAmount(UUID productId, Integer amountInStock) {
        if (cart.getProductsIdsAndAmounts().get(productId) == null) {
            return cart;
        }
        Integer currentAmount = cart.getProductsIdsAndAmounts().get(productId);
        if (currentAmount < amountInStock) {
            cart.getProductsIdsAndAmounts().put(productId, currentAmount + 1);
            cart.setTotalAmount(cart.getTotalAmount() + 1);
        }
        return cart;
    }

    public Cart decrementAmount(UUID productId) {
        if (cart.getProductsIdsAndAmounts().get(productId) == null) {
            return cart;
        }
        Integer currentAmount = cart.getProductsIdsAndAmounts().get(productId);
        if (currentAmount.equals(0)) {
            cart.getProductsIdsAndAmounts().remove(productId);
            return cart;
        }
        cart.getProductsIdsAndAmounts().put(productId, currentAmount - 1);
        cart.setTotalAmount(cart.getTotalAmount() - 1);
        return cart;
    }

    public Cart removeProduct(UUID productId) {
        if (cart.getProductsIdsAndAmounts().get(productId) == null) {
            return cart;
        }
        return decrementAmount(productId);
    }

    public Integer getTotalAmount() {
        return cart.getProductsIdsAndAmounts().values().stream().mapToInt(Integer::intValue).sum();
    }

    public List<UUID> getProductsIds() {
        return new ArrayList<>(cart.getProductsIdsAndAmounts().keySet());
    }

    public Map<UUID, Integer> tuples() {
        return cart.getProductsIdsAndAmounts();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart discard() {
        cart = new Cart();
        return cart;
    }
}
