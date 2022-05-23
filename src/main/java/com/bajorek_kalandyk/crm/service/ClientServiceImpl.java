package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> getAll()
    {
        return (List<Client>) repository.findAll();
    }

    @Override
    public void createClient(final Client client)
    {
        repository.save(client);
    }
}
