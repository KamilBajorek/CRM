package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.ClientStatistics;
import com.bajorek_kalandyk.crm.enumerations.StatisticsType;

public interface ClientStatisticsService
{
    ClientStatistics getStatisticsByType(final StatisticsType type);
}
