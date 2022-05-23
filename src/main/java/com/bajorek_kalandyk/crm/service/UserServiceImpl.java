package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public User createUser(final UserForm form) throws Exception
    {
        if (!isUserFormValid(form))
        {
            throw new Exception("Uzytkownik o podanym loginie juz istnieje!");
        }
        final Mail userMail = mailService.createMail(form.getEmail());
        final User newUser = User.builder()
                .login(form.getLogin())
                .name(form.getLogin())
                .surname(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(userMail)
                .build();
        return repository.save(newUser);
    }

    private Boolean isUserFormValid(final UserForm form)
    {
        User existingUser = repository.findByLogin(form.getLogin());
        return existingUser == null;
    }
}
