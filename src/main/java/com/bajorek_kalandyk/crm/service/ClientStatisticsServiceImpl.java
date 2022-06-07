package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.ClientStatistics;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.enumerations.StatisticsType;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.of;
import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIDNIGHT;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class ClientStatisticsServiceImpl implements ClientStatisticsService
{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientStatistics getStatisticsByType(final StatisticsType type)
    {
        final LocalDateTime startDate = getStartDate(type);
        final LocalDateTime endDate = of(LocalDate.now(), MAX);

        final List<Client> clients = clientRepository.findClientByCreateDateBetween(valueOf(startDate), valueOf(endDate));

        final Map<LocalDate, Long> data = new TreeMap<>(groupClientsByDate(clients));
        return ClientStatistics.builder()
                .ClientsTotal(clients.size())
                .dates(new ArrayList<>(data.keySet()))
                .clientCounts(new ArrayList<>(data.values()))
                .build();
    }

    private Map<LocalDate, Long> groupClientsByDate(final List<Client> clients)
    {
        return clients.stream()
                .collect(groupingBy(c -> c.getCreateDate().toLocalDateTime().toLocalDate(), counting()));
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
