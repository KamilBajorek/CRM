package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.domain.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface MailRepository extends CrudRepository<Mail, Long>
{
}
