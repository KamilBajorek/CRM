package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.exception.AddressDoesNotExistException;
import com.bajorek_kalandyk.crm.exception.ClientDoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface AddressService
{
    List<Address> getAll();

    Optional<Address> getById(Long id);

   Address createAddress(Address address);
   Address updateAddress(Address address) throws ClientDoesNotExistException, AddressDoesNotExistException;


}
