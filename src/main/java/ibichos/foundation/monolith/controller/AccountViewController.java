package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Customer;
import ibichos.foundation.monolith.service.AccountService;
import ibichos.foundation.monolith.service.CustomerService;

import ibichos.foundation.monolith.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountViewController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping({"/customer/", "/customer"})
    public String showCustomer(Model model) {
        if (accountService.isLoggedIn() && accountService.isCustomer()) {
            model.addAttribute("customer", accountService.getCustomer());
            model.addAttribute("account", accountService.getAccount());
            return "account";
        }
        return "redirect:/";
    }

    @PostMapping("/customer/update")
    public String updateCustomer(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String cpf,
                                 @RequestParam String address,
                                 @RequestParam String phone) {
        if (accountService.isLoggedIn() && accountService.isCustomer()) {
            accountService.getCustomer().setFirstName(firstName);
            accountService.getCustomer().setLastName(lastName);
            accountService.getCustomer().setCpf(cpf);
            accountService.getCustomer().setAddress(address);
            accountService.getCustomer().setPhone(phone);
            customerService.updateCustomer(accountService.getCustomer());
        }
        return "redirect:/customer";
    }

    @GetMapping({"/merchant/", "/merchant"})
    public String showMerchant(Model model) {
        if (accountService.isLoggedIn() && accountService.isMerchant()) {
            model.addAttribute("merchant", accountService.getMerchant());
            model.addAttribute("account", accountService.getAccount());
            return "account";
        }
        return "redirect:/";
    }

    @PostMapping("/merchant/update")
    public String updateMerchant(@RequestParam String socialName,
                                 @RequestParam String ownerName,
                                 @RequestParam String cnpj,
                                 @RequestParam String address,
                                 @RequestParam String phone) {
        if (accountService.isLoggedIn() && accountService.isMerchant()) {
            accountService.getMerchant().setSocialName(socialName);
            accountService.getMerchant().setOwnerName(ownerName);
            accountService.getMerchant().setCnpj(cnpj);
            accountService.getMerchant().setAddress(address);
            accountService.getMerchant().setPhone(phone);
            merchantService.updateMerchant(accountService.getMerchant());
        }
        return "redirect:/merchant";
    }
}
