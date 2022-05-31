package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientRestController
{
    private static final String ENDPOINT = "client";

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = ENDPOINT + "/getAll",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<List<Client>> getClients()
    {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientForm form)
    {
        try
        {
         final Client createdClient = clientService.createClient(form);
         return new ResponseEntity<>(createdClient, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
