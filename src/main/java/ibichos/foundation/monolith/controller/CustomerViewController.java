package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@Slf4j
public class CustomerViewController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String showCustomer(Model model) {
        if (!accountService.isCustomer()) {
            log.info("Is customer = " + accountService.isCustomer());
            return "redirect:/";
        }
        return "customer";
    }
}
