package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.domain.model.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PurchaseRepository extends CrudRepository<Purchase, Long>
{
    List<Purchase> findByDateBetween(Timestamp from, Timestamp to);
}
