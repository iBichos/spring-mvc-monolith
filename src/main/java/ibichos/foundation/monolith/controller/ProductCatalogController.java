package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.model.Cart;
import ibichos.foundation.monolith.service.ProductCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class ProductCatalogController {

    @Autowired
    ProductCatalogService productCatalogService;

    @GetMapping("/")
    public ModelAndView showCatalogPage(HttpSession session, ModelAndView model) {
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        model.setViewName("index");
        model.addObject("products", productCatalogService.getProductList());

        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("cart", cart);
            model.getModelMap().addAttribute("cartEmpty", false);
            model.getModelMap().addAttribute("cartCounter", cart.getProducts().size());
        }
        return model;
    }

    @GetMapping("/rations")
    public ModelAndView showCatalogPageFilteredByRations(HttpSession session, ModelAndView model) {
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        model.setViewName("index");
        model.addObject("products", productCatalogService.getProductList("rations"));

        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("cart", cart);
            model.getModelMap().addAttribute("cartEmpty", false);
            model.getModelMap().addAttribute("cartCounter", cart.getProducts().size());
        }

        return model;
    }

    @GetMapping("/collars")
    public ModelAndView showCatalogPageFilteredByCollars(HttpSession session, ModelAndView model) {
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        model.setViewName("index");
        model.addObject("products", productCatalogService.getProductList("collars"));

        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("cart", cart);
            model.getModelMap().addAttribute("cartEmpty", false);
            model.getModelMap().addAttribute("cartCounter", cart.getProducts().size());
        }
        return model;
    }

    @GetMapping("/toys")
    public ModelAndView showCatalogPageFilteredByToys(HttpSession session, ModelAndView model) {
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        model.setViewName("index");
        model.addObject("products", productCatalogService.getProductList("toys"));

        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("cart", cart);
            model.getModelMap().addAttribute("cartEmpty", false);
            model.getModelMap().addAttribute("cartCounter", cart.getProducts().size());
        }
        return model;
    }
}
