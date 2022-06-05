package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.ProductCategory;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ProductCategoryAlredyExistsException;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService
{
    List<ProductCategory> getAll();

    Optional<ProductCategory> getById(Long id);

    ProductCategory createProductCategory(String productCategory) throws ClientAlreadyExistsException, ProductCategoryAlredyExistsException;
}
