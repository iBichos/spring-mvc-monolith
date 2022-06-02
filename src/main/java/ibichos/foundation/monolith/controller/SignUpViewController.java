package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Customer;
import ibichos.foundation.monolith.model.Merchant;
import ibichos.foundation.monolith.service.AccountService;
import ibichos.foundation.monolith.service.CustomerService;
import ibichos.foundation.monolith.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sign-up")
public class SignUpViewController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/customer")
    public String showCustomerSignUp(Model model){
        model.addAttribute("isCustomer", true);
        model.addAttribute("isMerchant", false);
        return "sign-up";
    }

    @GetMapping("/merchant")
    public String showMerchantSignUp(Model model){
        model.addAttribute("isCustomer", false);
        model.addAttribute("isMerchant", true);
        return "sign-up";
    }

    @PostMapping("/customer")
    public String doCustomerSignUp(Model model,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String cpf,
                          @RequestParam String address,
                          @RequestParam String phone,
                          @RequestParam String email,
                          @RequestParam String emailRepeat,
                          @RequestParam String password,
                          @RequestParam String passwordRepeat) {

        boolean isValidPassword = accountService.validatePassword(password, passwordRepeat);
        boolean isValidEmail = accountService.validateEmail(email, emailRepeat);

        if (!isValidEmail) {
            model.addAttribute("emailUnmatched", true);
        } else if (!isValidPassword) {
            model.addAttribute("passwordUnmatched", true);
        } else {
            customerService.createCustomer(Customer.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .cpf(cpf)
                    .address(address)
                    .phone(phone)
                    .email(email)
                    .password(password)
                    .build());
        }
        return "redirect:/login/customer";
    }

    @PostMapping("/merchant")
    public String doMerchantSignUp(Model model,
                                   @RequestParam String socialName,
                                   @RequestParam String ownerName,
                                   @RequestParam String cnpj,
                                   @RequestParam String address,
                                   @RequestParam String phone,
                                   @RequestParam String email,
                                   @RequestParam String emailRepeat,
                                   @RequestParam String password,
                                   @RequestParam String passwordRepeat) {

        boolean isValidPassword = accountService.validatePassword(password, passwordRepeat);
        boolean isValidEmail = accountService.validateEmail(email, emailRepeat);

        if (!isValidEmail) {
            model.addAttribute("emailUnmatched", true);
        } else if (!isValidPassword) {
            model.addAttribute("passwordUnmatched", true);
        } else {
            merchantService.createMerchant(Merchant.builder()
                    .socialName(socialName)
                    .ownerName(ownerName)
                    .cnpj(cnpj)
                    .address(address)
                    .phone(phone)
                    .email(email)
                    .password(password)
                    .build());
        }
        return "redirect:/login/merchant";
    }
}
