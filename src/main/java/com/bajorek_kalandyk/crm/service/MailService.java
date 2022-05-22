package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.Client;
import com.bajorek_kalandyk.crm.domain.Mail;
import com.bajorek_kalandyk.crm.domain.Product;

import java.util.List;
import java.util.Optional;

public interface MailService
{
    List<Mail> getAll();

    Optional<Mail> getById(Long id);

    void createMail(Mail mail);
}
