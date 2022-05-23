package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressRestController
{
    private static final String ENDPOINT = "address";

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = ENDPOINT + "/getAll",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<List<Address>> getAddresses()
    {
        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/getById/{addressId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<Address> getAddress(@PathVariable("addressId") Long id)
    {
        Optional<Address> address = addressService.getById(id);
        return address.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<Address> createAddress(@RequestBody Address Address)
    {
        addressService.createAddress(Address);
        return new ResponseEntity<>(Address, HttpStatus.OK);
    }
}
