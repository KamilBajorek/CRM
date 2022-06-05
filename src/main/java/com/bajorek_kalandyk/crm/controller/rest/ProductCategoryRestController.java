package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.Product;
import com.bajorek_kalandyk.crm.domain.model.ProductCategory;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ProductCategoryAlredyExistsException;
import com.bajorek_kalandyk.crm.service.ProductCategoryService;
import com.bajorek_kalandyk.crm.service.ProductService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductCategoryRestController
{
    private static final String ENDPOINT = "productCategory";

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = ENDPOINT + "/getAll",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<List<ProductCategory>> getProductCategories()
    {
        return new ResponseEntity<>(productCategoryService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/getById/{productCategoryId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<ProductCategory> geProductCategory(@PathVariable("productCategoryId") Long id)
    {
        Optional<ProductCategory> ProductCategory = productCategoryService.getById(id);
        return ProductCategory.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody String category) throws ProductCategoryAlredyExistsException, ClientAlreadyExistsException
    {
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(category);
        return new ResponseEntity<>(createdProductCategory, HttpStatus.OK);
    }

}
