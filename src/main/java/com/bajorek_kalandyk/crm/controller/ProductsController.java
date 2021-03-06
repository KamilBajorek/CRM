package com.bajorek_kalandyk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController
{

    @GetMapping("/products")
    public String productsList(Model model)
    {
        return "Products";
    }

    @GetMapping("/products/add")
    public String addProducts(Model model)
    {
        return "AddProducts";
    }

    @GetMapping("/products/addCategory")
    public String addProductCategory(Model model)
    {
        return "AddProductCategory";
    }
}