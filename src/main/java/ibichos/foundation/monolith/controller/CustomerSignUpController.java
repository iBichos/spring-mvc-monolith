package ibichos.foundation.monolith.controller;

import ibichos.foundation.monolith.service.CustomerSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerSignUpController {

    @Autowired
    private CustomerSignUpService customerSignUpService;

    @GetMapping("/sign-up")
    public ModelAndView showSignUpPage(HttpSession session, ModelAndView model){
        if (session.getAttribute("active") != null) {
            if ((Boolean) session.getAttribute("active")) {
                model.setViewName("index");
                model.getModelMap().addAttribute("isLoggedIn", true);
            }
        }
        return model;
    }

    @PostMapping("/sign-up")
    public ModelAndView doLogin(HttpSession session, ModelAndView model,
                                @RequestParam String name,
                                @RequestParam String cpf,
                                @RequestParam String address,
                                @RequestParam String phone,
                                @RequestParam String email,
                                @RequestParam String emailRepeat,
                                @RequestParam String password,
                                @RequestParam String passwordRepeat) {

        boolean isValidPassword = customerSignUpService.validatePassword(password, passwordRepeat);
        boolean isValidEmail = customerSignUpService.validateEmail(email, emailRepeat);

        if (!isValidEmail) {
            model.getModelMap().addAttribute("emailUnmatched", true);
        } else if (!isValidPassword) {
            model.getModelMap().addAttribute("passwordUnmatched", true);
        } else {
            try {
                customerSignUpService.createNewCustomer(name, email, password, cpf, phone, address);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            model.setViewName("sign-up-success");
        }
        return model;
    }
}
