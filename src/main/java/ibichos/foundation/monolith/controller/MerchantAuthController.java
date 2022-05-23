package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.MerchantAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MerchantAuthController {

    @Autowired
    MerchantAuthService merchantAuthService;

    @GetMapping("/merchant-login")
    public ModelAndView showLoginPage(HttpSession session, ModelAndView model){
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.setViewName("redirect:/");
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        model.setViewName("merchant-login");
        return model;
    }

    @PostMapping("/merchant-login")
    public ModelAndView doLogin(HttpSession session, ModelAndView model,
                                @RequestParam String email,
                                @RequestParam String password) {

        if (!merchantAuthService.login(email, password)) {
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
