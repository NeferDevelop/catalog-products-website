package com.neferdevelop.catalog.controller;

import com.neferdevelop.catalog.form.UserRepresentation;
import com.neferdevelop.catalog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserRepresentation());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid UserRepresentation userRepresentation, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }
        if (!userRepresentation.getPassword().equals(userRepresentation.getRepeatPassword())){
            bindingResult.rejectValue("password", "Passwords don't match");
            return "register";
        }
        userService.create(userRepresentation);
        return "redirect:/login";
    }

}
