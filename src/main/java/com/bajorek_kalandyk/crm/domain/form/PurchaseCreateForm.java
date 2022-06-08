package com.bajorek_kalandyk.crm.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class PurchaseCreateForm
{
    @NotNull
    private final Long productId;

    @NotNull
    private final Long clientId;
}
