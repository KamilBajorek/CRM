package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<User> getAll();

    Optional<User> getById(Long id);

    User createUser(UserForm form) throws Exception;
}
