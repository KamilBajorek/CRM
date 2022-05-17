package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.Client;
import com.bajorek_kalandyk.crm.domain.Product;
import com.bajorek_kalandyk.crm.service.ClientService;
import com.bajorek_kalandyk.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController
{
    private static final String ENDPOINT = "product";

    @Autowired
    private ProductService productService;

    @RequestMapping(value = ENDPOINT + "/getAll",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<List<Product>> getProducts()
    {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/getById/{productId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long id)
    {
        Optional<Product> product = productService.getById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
