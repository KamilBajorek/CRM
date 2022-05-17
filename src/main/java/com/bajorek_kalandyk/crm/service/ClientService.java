package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.Client;

import java.util.List;

public interface ClientService
{
    List<Client> getAll();

    void createClient(Client client);
}
