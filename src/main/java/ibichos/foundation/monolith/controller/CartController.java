package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Cart;
import ibichos.foundation.monolith.model.Product;
import ibichos.foundation.monolith.service.CartService;
import ibichos.foundation.monolith.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/status")
    public Cart status() {
        Cart cart = cartService.getCart();
        if (!cart.getProductsIdsAndAmounts().isEmpty()) {
            cart.setTotalPrice(BigDecimal.ZERO);
            cart.getProductsIdsAndAmounts().forEach((productId, amount) -> {
                Product product = productService.getProduct(productId);
                BigDecimal totalPrice = cart.getTotalPrice();
                BigDecimal productTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(amount));
                cart.setTotalPrice(totalPrice.add(productTotalPrice));
            });
            cart.setIsEmpty(false);
        } else {
            cart.setTotalPrice(BigDecimal.ZERO);
            cart.setIsEmpty(true);
        }


        cartService.setCart(cart);
        return cartService.getCart();
    }

    @PutMapping("/add/{productId}")
    public Cart addProduct(@PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        cartService.addProduct(product.getProductId(), product.getAmountInStock());
        return cartService.getCart();
    }

    @DeleteMapping("/remove/{productId}")
    public Cart removeProduct(@PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        cartService.removeProduct(product.getProductId());
        return cartService.getCart();
    }

    @PatchMapping("/increment/{productId}")
    public Cart incrementProduct(@PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        cartService.incrementAmount(product.getProductId(), product.getAmountInStock());
        return cartService.getCart();
    }

    @PatchMapping("/decrement/{productId}")
    public Cart decrementProduct(@PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        cartService.decrementAmount(product.getProductId());
        return cartService.getCart();
    }
}
