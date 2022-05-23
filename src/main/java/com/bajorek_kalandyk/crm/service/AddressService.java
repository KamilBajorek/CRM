package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService
{
    List<Address> getAll();

    Optional<Address> getById(Long id);

    void createAddress(Address address);
}
