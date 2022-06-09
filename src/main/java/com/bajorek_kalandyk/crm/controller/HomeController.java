package com.bajorek_kalandyk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    @RequestMapping("/")
    @GetMapping("/")
    public String alsoHomePage(Model model)
    {
        return "home";
    }
}