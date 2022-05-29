package com.bajorek_kalandyk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddClientController
{

    @GetMapping("/addclients")
    public String addClient(Model model)
    {
        return "AddClients";
    }
}