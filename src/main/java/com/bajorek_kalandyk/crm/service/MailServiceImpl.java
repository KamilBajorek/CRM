package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.Mail;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
    public void createMail(final Mail mail)
    {
        repository.save(mail);
    }
}
