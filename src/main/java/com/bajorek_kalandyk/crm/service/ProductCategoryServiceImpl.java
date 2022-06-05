package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.ProductCategory;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ProductCategoryAlredyExistsException;
import com.bajorek_kalandyk.crm.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNullElseGet;

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
    public ProductCategory createProductCategory(final String productCategory) throws ProductCategoryAlredyExistsException
    {
        final ProductCategory currentProductCategory = repository.findByName(productCategory);
        if(currentProductCategory != null)
        {
            throw new ProductCategoryAlredyExistsException("Kategoria o podanej nazwie już istnieje!");
        }
        return requireNonNullElseGet(currentProductCategory, () -> repository.save(ProductCategory.builder().name(productCategory).build()));
    }
}
