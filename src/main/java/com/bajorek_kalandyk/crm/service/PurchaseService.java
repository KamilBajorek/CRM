package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.PurchaseCreateForm;
import com.bajorek_kalandyk.crm.domain.model.Purchase;

import java.util.List;

public interface PurchaseService
{
    List<Purchase> getAll();
    
    Purchase createPurchase(PurchaseCreateForm purchase) throws Exception;
}
