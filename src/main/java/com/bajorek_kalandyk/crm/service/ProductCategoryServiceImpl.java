package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.ProductCategory;
import com.bajorek_kalandyk.crm.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService
{
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> getAll()
    {
        return (List<ProductCategory>) repository.findAll();
    }

    @Override
    public Optional<ProductCategory> getById(final Long id)
    {
        return repository.findById(id);
    }

    @Override
    public ProductCategory createProductCategory(final ProductCategory productCategory)
    {
        return repository.save(productCategory);
    }
}
