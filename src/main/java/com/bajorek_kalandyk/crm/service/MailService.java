package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Mail;

import java.util.List;
import java.util.Optional;

public interface MailService
{
    List<Mail> getAll();

    Optional<Mail> getById(Long id);

    Mail createMail(String mail);
}
