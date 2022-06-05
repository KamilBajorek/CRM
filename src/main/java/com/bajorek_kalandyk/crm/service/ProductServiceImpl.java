package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.form.ProductForm;
import com.bajorek_kalandyk.crm.domain.model.*;
import com.bajorek_kalandyk.crm.exception.AuthenticatedUserMissingException;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ProductAlreadyExistsException;
import com.bajorek_kalandyk.crm.repository.ProductCategoryRepository;
import com.bajorek_kalandyk.crm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.bajorek_kalandyk.crm.common.AuthenticationHelper.GetCurrentUser;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

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
    public Product createProduct(final ProductForm form) throws ProductAlreadyExistsException
    {
        validateForm(form);
        final Product newProduct = Product.builder()
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .category(form.)
                .createDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return repository.save(newProduct);
    }

    private void validateForm(final ProductForm form) throws ProductAlreadyExistsException
    {
        final Product existingProductName = repository.findByName(form.getName());
        if (existingProductName != null)
        {
            throw new ProductAlreadyExistsException("Produkt o podanej nazwie już istnieje!");
        }
    }
}
