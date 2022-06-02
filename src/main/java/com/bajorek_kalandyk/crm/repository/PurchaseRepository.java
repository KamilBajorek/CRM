package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.domain.model.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PurchaseRepository extends CrudRepository<Purchase, Long>
{
    // @Query("SELECT p FROM Purchases p WHERE p.date >= ?1 and p.date <= ?2")
    // List<Purchase> search(LocalDate dateFrom, LocalDate dateTo);

    List<Purchase> findByDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
