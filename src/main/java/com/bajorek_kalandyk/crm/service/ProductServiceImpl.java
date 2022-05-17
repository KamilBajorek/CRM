package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.Product;
import com.bajorek_kalandyk.crm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll()
    {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Optional<Product> getById(final Long id)
    {
        return repository.findById(id);
    }

    @Override
    public void createProduct(final Product product)
    {
        repository.save(product);
    }
}
