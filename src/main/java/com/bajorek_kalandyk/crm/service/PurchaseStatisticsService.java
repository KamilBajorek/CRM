package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.PerUserPurchaseStatistics;
import com.bajorek_kalandyk.crm.domain.PurchaseStatistics;
import com.bajorek_kalandyk.crm.enumerations.StatisticsType;

public interface PurchaseStatisticsService
{
    PurchaseStatistics getStatisticsByType(StatisticsType type);

    PerUserPurchaseStatistics getPerUserStatisticsByType(StatisticsType type);
}
