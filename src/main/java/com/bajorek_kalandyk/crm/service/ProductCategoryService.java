package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService
{
    List<ProductCategory> getAll();

    Optional<ProductCategory> getById(Long id);

    ProductCategory createProductCategory(ProductCategory productCategory);
}
