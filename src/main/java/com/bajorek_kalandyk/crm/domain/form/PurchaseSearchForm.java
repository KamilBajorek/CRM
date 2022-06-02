package com.bajorek_kalandyk.crm.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class PurchaseSearchForm
{
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
}
