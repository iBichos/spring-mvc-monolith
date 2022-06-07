package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.AccountService;
import ibichos.foundation.monolith.service.CartService;
import ibichos.foundation.monolith.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderViewController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/create")
    public String createOrder(Model model) {;
        model.addAttribute("order", orderService.newOrder(cartService.status(), accountService.getCustomer()));
        return "order";
    }
}
