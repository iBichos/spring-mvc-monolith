package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Product;
import ibichos.foundation.monolith.service.CartService;
import ibichos.foundation.monolith.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartViewController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showCart(Model model) {
        Map<Product, Integer> tuples = new HashMap<>();
        List<UUID> productsIdsToRemove = new ArrayList<>();
        cartService.tuples().forEach((productId, amount) -> {
            if (amount.equals(0)) {
                productsIdsToRemove.add(productId);
            } else {
                tuples.put(productService.getProduct(productId), amount);
            }
        });
        productsIdsToRemove.forEach(productId -> cartService.tuples().remove(productId));
        model.addAttribute("tuples", tuples);
        return "cart";
    }
}
