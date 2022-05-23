package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.domain.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AddressRepository extends CrudRepository<Address, Long>
{
}
