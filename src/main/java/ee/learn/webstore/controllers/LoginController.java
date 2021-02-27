package ee.learn.webstore.controllers;

import ee.learn.webstore.entities.UserEE;
import ee.learn.webstore.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/user/create")
    String createUser(Model model){
        model.addAttribute("user", new UserEE());
        return "createUser";
    }

    @PostMapping("/user/create")
    RedirectView createUserPost(UserEE userEE){
        userDetailsService.createUser(userEE);
        return new RedirectView("/product/all");
    }
}
