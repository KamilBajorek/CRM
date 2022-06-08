package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.PerUserPurchaseStatistics;
import com.bajorek_kalandyk.crm.domain.PurchaseStatistics;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Purchase;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.enumerations.StatisticsType;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.PurchaseRepository;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.of;
import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIDNIGHT;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class PurchaseStatisticsServiceImpl implements PurchaseStatisticsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public PurchaseStatistics getStatisticsByType(final StatisticsType type)
    {
        final LocalDateTime startDate = getStartDate(type);
        final LocalDateTime endDate = of(LocalDate.now(), MAX);

        final List<Purchase> purchases = purchaseRepository.findByDateBetween(valueOf(startDate), valueOf(endDate));

        final Map<String, Long> data = groupPurchasesByProduct(purchases);

        return PurchaseStatistics.builder()
                .productNames(new ArrayList<>(data.keySet()))
                .productsSold(new ArrayList<>(data.values()))
                .build();
    }

    @Override
    public PerUserPurchaseStatistics getPerUserStatisticsByType(final StatisticsType type)
    {
        final LocalDateTime startDate = getStartDate(type);
        final LocalDateTime endDate = of(LocalDate.now(), MAX);

        final List<Purchase> purchases = purchaseRepository.findByDateBetween(valueOf(startDate), valueOf(endDate));

        final Map<User, Long> data = groupPurchasesByUser(purchases);

        return PerUserPurchaseStatistics.builder()
                .users(new ArrayList<>(data.keySet()))
                .productsSold(new ArrayList<>(data.values()))
                .build();
    }

    private Map<String, Long> groupPurchasesByProduct(final List<Purchase> purchases)
    {
        return purchases.stream()
                .collect(groupingBy(p -> p.getProduct().getName(), counting()));
    }

    private Map<User, Long> groupPurchasesByUser(final List<Purchase> purchases)
    {
        return purchases.stream()
                .map(p -> getClientManager(p.getClientId()))
                .filter(Optional::isPresent)
                .collect(groupingBy(Optional::get, counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Optional<User> getClientManager(final Long clientId)
    {
        Optional<Client> client = clientRepository.findById(clientId);

        return client.flatMap(value -> userRepository.findById(value.getManagerId()));
    }

    private LocalDateTime getStartDate(final StatisticsType type)
    {
        final LocalDate today = LocalDate.now();

        switch (type)
        {
            case ONE_WEEK:
                return of(today.minusWeeks(1), MIDNIGHT);
            case TWO_WEEKS:
                return of(today.minusWeeks(2), MIDNIGHT);
            case MONTH:
                return of(today.minusMonths(1), MIDNIGHT);
            default:
                return of(today, MIDNIGHT);
        }
    }
}
