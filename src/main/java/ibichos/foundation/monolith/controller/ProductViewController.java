package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Product;
import ibichos.foundation.monolith.service.AccountService;
import ibichos.foundation.monolith.service.MerchantService;
import ibichos.foundation.monolith.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductViewController {

    @Autowired
    private AccountService accountService;

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

    @GetMapping("/create")
    public String createProduct(Model model) {
        if (accountService.isLoggedIn() && accountService.isMerchant()) {
            model.addAttribute("create", true);
            model.addAttribute("edit", false);
            model.addAttribute("product", productService.getDummyProduct());
            return "product-create-edit";
        }
        return "redirect:/";
    }

    @GetMapping("/{productId}/edit")
    public String editProduct(Model model, @PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        if (accountService.isLoggedIn() &&
            accountService.isMerchant() &&
            product.getMerchantId().equals(accountService.getMerchant().getMerchantId())) {
            model.addAttribute("create", false);
            model.addAttribute("edit", true);
            model.addAttribute("product", product);
            return "product-create-edit";
        }
        return "redirect:/";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        if (accountService.isLoggedIn() &&
            accountService.isMerchant() &&
            product.getMerchantId().equals(accountService.getMerchant().getMerchantId())) {
            productService.deleteProduct(product);
            return "redirect:/merchant/";
        }
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam BigDecimal price,
                                @RequestParam Integer amountInStock,
                                @RequestParam MultipartFile image) {
        if (accountService.isLoggedIn() && accountService.isMerchant()) {
            UUID productId = UUID.randomUUID();
            productService.createProduct(Product
                            .builder()
                            .productId(productId)
                            .merchantId(accountService.getMerchant().getMerchantId())
                            .name(name)
                            .description(description)
                            .price(price)
                            .monetaryUnit("BRL")
                            .amountInStock(amountInStock)
                            .build());

            if (!image.isEmpty()) {
                log.info("Received " + image.getOriginalFilename() + " saved as " + productId + ".png");
            }
            return "redirect:/merchant/";
        }
        return "redirect:/";
    }

    @PostMapping("/{productId}/edit")
    public String editProduct(@PathVariable UUID productId,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String price,
                              @RequestParam Integer amountInStock,
                              @RequestParam MultipartFile image) {
        Product product = productService.getProduct(productId);
        if (accountService.isLoggedIn() &&
            accountService.isMerchant() &&
            product.getMerchantId().equals(accountService.getMerchant().getMerchantId())) {
            product.setName(name);
            product.setDescription(description);
            product.setPrice(new BigDecimal(price));
            product.setAmountInStock(amountInStock);
            productService.updateProduct(product);
            if (!image.isEmpty()) {
                log.info("Received " + image.getOriginalFilename() + " saved as " + productId + ".png");
            }
            return "redirect:/merchant/";
        }
        return "redirect:/";
    }

    @GetMapping("/merchant/{merchantId}")
    public String listMerchantProducts(Model model, @PathVariable UUID merchantId) {
        if (accountService.isLoggedIn() &&
            accountService.isMerchant()) {
            model.addAttribute("products", productService.getProductsByMerchantId(merchantId));
            return "product-list";
        }
        return "redirect:/";
    }
}