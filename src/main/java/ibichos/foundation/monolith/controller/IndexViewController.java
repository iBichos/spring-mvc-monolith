package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Category;
import ibichos.foundation.monolith.model.Product;
import ibichos.foundation.monolith.service.CategoryService;
import ibichos.foundation.monolith.service.ProductCategoryService;
import ibichos.foundation.monolith.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class IndexViewController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String showIndex(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/category/{name}")
    public String showIndexFilteredByCategoryName(Model model, @PathVariable String name) {
        Category category      = categoryService.getCategory(name);
        List<UUID> productsIds = productCategoryService.getProductsIds(category.getCategoryId());
        List<Product> products = productService.getProducts(productsIds);

        model.addAttribute("products", products);
        return "index";
    }
}
