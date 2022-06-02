package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.CategoryService;
import ibichos.foundation.monolith.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes({"products"})
public class IndexViewController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/category/{name}")
    public String showIndexFilteredByCategoryName(Model model, @PathVariable String name) {
        UUID categoryId = categoryService.getCategory(name).getCategoryId();
        List<UUID> productsIds = categoryService.getProductsIds(categoryId);
        model.addAttribute("products", productService.getProducts(productsIds));
        return "index";
    }
}
