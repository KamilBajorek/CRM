package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.Product;
import com.bajorek_kalandyk.crm.domain.User;
import com.bajorek_kalandyk.crm.repository.ProductRepository;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll()
    {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> getById(final Long id)
    {
        return repository.findById(id);
    }

    @Override
    public void createUser(final User user)
    {
        repository.save(user);
    }
}
