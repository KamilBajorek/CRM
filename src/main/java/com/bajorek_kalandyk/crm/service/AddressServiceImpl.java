package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService
{
    @Autowired
    private AddressRepository repository;

    @Override
    public List<Address> getAll()
    {
        return (List<Address>) repository.findAll();
    }

    @Override
    public Optional<Address> getById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public void createAddress(final Address address)
    {
        repository.save(address);
    }
}
