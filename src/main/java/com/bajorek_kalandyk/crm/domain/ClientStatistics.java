package com.bajorek_kalandyk.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class ClientStatistics
{
    private final List<LocalDate> dates;
    private final List<Long> clientCounts;

    private final Integer ClientsTotal;
}
