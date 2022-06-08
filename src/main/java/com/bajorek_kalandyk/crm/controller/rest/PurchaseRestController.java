package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.form.PurchaseCreateForm;
import com.bajorek_kalandyk.crm.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PurchaseRestController
{
    private static final String ENDPOINT = "purchase";

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<?> createPurchase(@RequestBody @Valid PurchaseCreateForm form)
    {
        try
        {
            return new ResponseEntity<>(purchaseService.createPurchase(form), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
