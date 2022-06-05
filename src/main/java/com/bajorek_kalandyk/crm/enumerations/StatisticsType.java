package com.bajorek_kalandyk.crm.enumerations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatisticsType
{
    ONE_DAY(1L),
    ONE_WEEK(2L),
    TWO_WEEKS(3L),
    MONTH(4L);

    private final Long id;

    public static StatisticsType getById(Long id)
    {
        for (StatisticsType e : values())
        {
            if (e.id.equals(id)) return e;
        }
        return ONE_DAY;
    }
}
