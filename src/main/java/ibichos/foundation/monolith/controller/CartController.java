package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Cart;
import ibichos.foundation.monolith.service.CartService;
import ibichos.foundation.monolith.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/status")
    public Cart status() {
        return cartService.status();
    }

    @PutMapping("/add/{productId}")
    public Cart addProduct(@PathVariable UUID productId) {
        cartService.add(productService.getProduct(productId));
        return cartService.status();
    }

    @DeleteMapping("/remove/{productId}")
    public Cart removeProduct(@PathVariable UUID productId) {
        cartService.remove(productService.getProduct(productId));
        return cartService.status();
    }

    @PatchMapping("/increment/{productId}")
    public Cart incrementProduct(@PathVariable UUID productId) {
        cartService.increment(productService.getProduct(productId));
        return cartService.status();
    }

    @PatchMapping("/decrement/{productId}")
    public Cart decrementProduct(@PathVariable UUID productId) {
        cartService.decrement(productService.getProduct(productId));
        return cartService.status();
    }
}
