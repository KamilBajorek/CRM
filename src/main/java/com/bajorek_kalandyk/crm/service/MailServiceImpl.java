package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNullElseGet;

@Service
public class MailServiceImpl implements MailService
{
    @Autowired
    private MailRepository repository;

    @Override
    public List<Mail> getAll()
    {
        return (List<Mail>) repository.findAll();
    }

    @Override
    public Optional<Mail> getById(final Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Mail createMail(final String mail)
    {
        Mail currentMail = repository.findMailByMail(mail);
        return requireNonNullElseGet(currentMail, () -> repository.save(Mail.builder().mail(mail).build()));
    }
}
