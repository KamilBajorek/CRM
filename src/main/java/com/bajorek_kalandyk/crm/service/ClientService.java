package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.exception.AuthenticatedUserMissingException;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;

import java.util.List;

public interface ClientService
{
    List<Client> getAll();

    Client createClient(ClientForm form) throws ClientAlreadyExistsException, AuthenticatedUserMissingException;
}
