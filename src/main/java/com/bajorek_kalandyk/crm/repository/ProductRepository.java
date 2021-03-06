package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends CrudRepository<Product, Long>
{
    Product findByName(String name);
}
