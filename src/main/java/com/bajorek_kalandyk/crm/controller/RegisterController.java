package com.bajorek_kalandyk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController
{
    @GetMapping("/register")
    public String registerPage(Model model, String error)
    {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        return "Register";
    }
}
