package com.bajorek_kalandyk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddProductsController
{

    @GetMapping("/addProducts")
    public String addProducts(Model model)
    {
        return "AddProducts";
    }
}