package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.common.AuthenticationHelper;
import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.form.ClientUpdateForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = ENDPOINT + "/getByManagerId",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<?> getClientsByManager()
    {
        try
        {
            final Long managerId = AuthenticationHelper.GetCurrentUser().getId();
            return new ResponseEntity<>(clientService.getByManagerId(managerId), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = ENDPOINT + "/getById/{clientId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<Client> getClient(@PathVariable("clientId") Long id)
    {
        Optional<Client> client = clientService.getById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/update",
            method = RequestMethod.PUT,
            headers = "Accept=application/json")
    public ResponseEntity<?> updateClient(@RequestBody @Valid ClientUpdateForm form)
    {
        try
        {
            final Client updatedClient = clientService.updateClient(form);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
