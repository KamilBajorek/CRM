package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<User> getAll();

    Optional<User> getById(Long id);

    void createUser(User product);
}
