package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.exception.UserAlreadyExistsException;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private MailRepository mailRepository;

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
    public User createUser(final UserForm form) throws UserAlreadyExistsException
    {
        validateForm(form);
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

    private void validateForm(final UserForm form) throws UserAlreadyExistsException
    {
        final User existingUserByLogin = repository.findByLogin(form.getLogin());
        final Mail existingMail = mailRepository.findMailByMail(form.getEmail());
        if (existingMail != null)
        {
            final User existingMailByMail = repository.findByEmail(existingMail);
            if (existingMailByMail != null)
            {
                throw new UserAlreadyExistsException("Użytkownik z podanym adresem email już istnieje!");
            }
        }
        if (existingUserByLogin != null)
        {
            throw new UserAlreadyExistsException("Użytkownik z podanym loginem już istnieje!");
        }
    }
}
