package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService
{
    List<Product> getAll();

    Optional<Product> getById(Long id);

    void createProduct(Product product);
}
