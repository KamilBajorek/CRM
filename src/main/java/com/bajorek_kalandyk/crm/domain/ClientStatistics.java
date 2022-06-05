package com.bajorek_kalandyk.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class ClientStatistics
{
    private final Map<LocalDate, Long> ClientsAddedByDate;

    private final Integer ClientsTotal;
}
