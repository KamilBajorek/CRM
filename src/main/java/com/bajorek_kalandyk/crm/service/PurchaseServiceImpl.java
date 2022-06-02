package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.PurchaseCreateForm;
import com.bajorek_kalandyk.crm.domain.form.PurchaseSearchForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Product;
import com.bajorek_kalandyk.crm.domain.model.Purchase;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.ProductRepository;
import com.bajorek_kalandyk.crm.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
public class PurchaseServiceImpl implements PurchaseService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PurchaseRepository repository;

    @Override
    public List<Purchase> getAll()
    {
        return (List<Purchase>) repository.findAll();
    }

    @Override
    public List<Purchase> searchPurchases(final PurchaseSearchForm searchForm)
    {
        return repository.findByDateBetween(searchForm.getDateFrom(), searchForm.getDateTo());
    }

    @Override
    public Purchase createPurchase(final PurchaseCreateForm createForm) throws Exception
    {
        final Optional<Product> product = productRepository.findById(createForm.getProductId());
        final Optional<Client> client = clientRepository.findById(createForm.getClientId());

        if (product.isEmpty())
        {
            throw new Exception("Produkt o podanym ID nie istnieje.");
        }
        if (client.isEmpty())
        {
            throw new Exception("Klient o podanym ID nie istnieje.");
        }
        final Purchase purchase = Purchase.builder()
                .clientId(createForm.getClientId())
                .product(product.get())
                .date(now())
                .build();

        return repository.save(purchase);
    }
}
