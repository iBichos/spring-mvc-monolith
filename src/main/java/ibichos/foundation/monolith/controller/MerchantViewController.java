package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
@Slf4j
public class MerchantViewController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String showMerchant(Model model) {
        if (!accountService.isMerchant()) {
            log.info("Is merchant = " + accountService.isMerchant());
            return "redirect:/";
        }
        return "merchant";
    }
}
