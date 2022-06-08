package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends CrudRepository<Client, Long>
{
    Client findClientByEmail(Mail mail);

    List<Client> findAll(Sort id);

    List<Client> findByManagerId(Long managerId);

    List<Client> findClientByCreateDateBetween(Timestamp from, Timestamp to);
}
