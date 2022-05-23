package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.CustomerAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerAuthController {

    @Autowired
    CustomerAuthService customerAuthService;

    @GetMapping("/login")
    public ModelAndView showLoginPage(HttpSession session, ModelAndView model){
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.setViewName("redirect:/");
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        return model;
    }

    @GetMapping("/logout")
    public ModelAndView doLogout(HttpSession session, ModelAndView model){
        if (session.getAttribute("active") != null) {
            session.setAttribute("active", false);
        }

        model.setViewName("redirect:/");
        model.getModelMap().addAttribute("isLoggedIn", false);
        return model;
    }

    @PostMapping("/login")
    public ModelAndView doLogin(HttpSession session, ModelAndView model,
                                               @RequestParam String email,
                                               @RequestParam String password) {

        if (!customerAuthService.login(email, password)) {
            session.setAttribute("active", false);
            model.getModelMap().addAttribute("loginFailure", true);
        } else {
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("active", true);
            model.setViewName("redirect:/");
            model.getModelMap().addAttribute("isLoggedIn", true);
        }

        return model;
    }
}