package com.bajorek_kalandyk.crm.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class PurchaseCreateForm
{
    private final Long productId;
    private final Long clientId;
}
