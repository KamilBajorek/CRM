package com.bajorek_kalandyk.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class PurchaseStatistics
{
    private final List<String> productNames;

    private final List<Long> productsSold;
}
