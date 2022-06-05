package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.ProductForm;
import com.bajorek_kalandyk.crm.domain.model.Product;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ProductAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface ProductService
{
    List<Product> getAll();

    Optional<Product> getById(Long id);

    Product createProduct(ProductForm form)  throws ProductAlreadyExistsException;
}
