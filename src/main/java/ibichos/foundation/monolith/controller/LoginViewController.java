package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.AccountService;
import ibichos.foundation.monolith.service.CartService;
import ibichos.foundation.monolith.service.CustomerService;
import ibichos.foundation.monolith.service.MerchantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"email", "isLoggedIn", "isCustomer", "isMerchant"})
public class LoginViewController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/login/customer")
    public String showCustomerLogin(Model model) {
        if (accountService.isLoggedIn()) {
            return "redirect:/";
        }
        accountService.setIsCustomer(true);
        model.addAttribute("isCustomer", accountService.isCustomer());
        model.addAttribute("isMerchant", accountService.isMerchant());
        return "login";
    }

    @PostMapping("/login/customer")
    public String doCustomerLogin(Model model, @RequestParam String email, @RequestParam String password) {
        if (!accountService.isLoggedIn()) {
            accountService.setIsCustomer(true);
            model.addAttribute("email", email);
            model.addAttribute("isLoggedIn", accountService.login(customerService.getCustomer(email, password)));
            model.addAttribute("isCustomer", accountService.isCustomer());
            model.addAttribute("isMerchant", accountService.isMerchant());
        }
        return "redirect:/";
    }

    @GetMapping("/login/merchant")
    public String showMerchantLogin(Model model) {
        if (accountService.isLoggedIn()) {
            return "redirect:/";
        }
        accountService.setIsMerchant(true);
        model.addAttribute("isCustomer", accountService.isCustomer());
        model.addAttribute("isMerchant", accountService.isMerchant());
        return "login";
    }

    @PostMapping("/login/merchant")
    public String doMerchantLogin(Model model, @RequestParam String email, @RequestParam String password) {
        if (!accountService.isLoggedIn()) {
            accountService.setIsMerchant(true);
            model.addAttribute("email", email);
            model.addAttribute("isLoggedIn", accountService.login(merchantService.getMerchant(email, password)));
            model.addAttribute("isCustomer", accountService.isCustomer());
            model.addAttribute("isMerchant", accountService.isMerchant());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String doLogout(Model model) {
        model.addAttribute("email", null);
        model.addAttribute("isLoggedIn", accountService.logout());
        cartService.discard();
        return "redirect:/";
    }
}
