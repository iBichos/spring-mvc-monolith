package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Cart;
import ibichos.foundation.monolith.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/add-to-cart/{productId}")
    public ModelAndView addToCart(HttpSession session, ModelAndView model,
                                 @PathVariable String productId){

        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            cart.getProducts().add(cartService.getProductById(UUID.fromString(productId)));
            session.setAttribute("cart", cart);
        } else {
            Cart cart = new Cart();
            cart.getProducts().add(cartService.getProductById(UUID.fromString(productId)));
            session.setAttribute("cart", cart);
        }
        model.getModelMap().addAttribute("cartEmpty");

        model.setViewName("redirect:/");
        return model;
    }
}
