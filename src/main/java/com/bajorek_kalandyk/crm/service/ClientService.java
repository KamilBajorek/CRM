package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.form.ClientUpdateForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.exception.AddressDoesNotExistException;
import com.bajorek_kalandyk.crm.exception.AuthenticatedUserMissingException;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ClientDoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface ClientService
{
    List<Client> getAll();

    List<Client> getByManagerId(Long managerId);

    Optional<Client> getById(Long id);

    Client createClient(ClientForm form) throws ClientAlreadyExistsException, AuthenticatedUserMissingException;

    Client updateClient(ClientUpdateForm form) throws ClientDoesNotExistException, AddressDoesNotExistException;
}
