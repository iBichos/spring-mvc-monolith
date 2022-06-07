package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartViewController {
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String showCart(Model model) {
        model.addAttribute("productAmounts", cartService.productAmounts());
        return "cart";
    }
}
