package com.bajorek_kalandyk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClientController
{
    @GetMapping("/clients")
    public String clientList(Model model)
    {
        return "Clients";
    }

    @GetMapping("/clients/add")
    public String addClient(Model model)
    {
        return "AddClients";
    }

    @GetMapping("/clients/{id}")
    public String updateClient(Model model, @PathVariable Long id)
    {
        model.addAttribute("id", id);
        return "UpdateClient";
    }
}