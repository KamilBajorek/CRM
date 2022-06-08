package com.bajorek_kalandyk.crm.domain;

import com.bajorek_kalandyk.crm.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class PerUserPurchaseStatistics
{
    private final List<User> users;

    private final List<Long> productsSold;
}
