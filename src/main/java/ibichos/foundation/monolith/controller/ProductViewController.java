package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.AccountService;
import ibichos.foundation.monolith.service.MerchantService;
import ibichos.foundation.monolith.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductViewController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/{productId}")
    public String showProduct(Model model, @PathVariable UUID productId) {
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("merchant", merchantService.getMerchant(productService.getProduct(productId).getMerchantId()));
        return "product";
    }
}