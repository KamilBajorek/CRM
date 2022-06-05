package com.bajorek_kalandyk.crm.repository;

import com.bajorek_kalandyk.crm.AbstractIntegrationTest;
import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIDNIGHT;
import static java.time.LocalTime.NOON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryIT extends AbstractIntegrationTest
{
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private ClientRepository repository;

    @Test
    public void returnInsertedClientWhenSearchingById()
    {
        // given
        final Mail clientMail = mailRepository.save(Mail.builder().mail("client@mail.com").build());
        final Address clientAddress = addressRepository.save(Address.builder().build());
        final Client createdClient = repository.save(Client.builder()
                .address(clientAddress)
                .email(clientMail)
                .name("Test")
                .surname("Test")
                .createDate(valueOf(now()))
                .managerId(1L)
                .build());

        // when
        Optional<Client> result = repository.findById(createdClient.getId());

        // then
        assertTrue(result.isPresent());
        assertThat(createdClient.getId()).isNotNull();
        assertThat(createdClient.getEmail().getId()).isEqualTo(clientMail.getId());
    }

    @Test
    public void returnInsertedClientWhenSearchingByMail()
    {
        // given
        final Mail clientMail = mailRepository.save(Mail.builder().mail("client@mail.com").build());
        final Address clientAddress = addressRepository.save(Address.builder().build());
        final Client createdClient = repository.save(Client.builder()
                .address(clientAddress)
                .email(clientMail)
                .name("Test")
                .surname("Test")
                .createDate(valueOf(now()))
                .managerId(1L)
                .build());

        // when
        Client result = repository.findClientByEmail(clientMail);

        // then
        assertThat(result).isNotNull();
        assertThat(createdClient.getId()).isNotNull();
        assertThat(createdClient.getEmail().getId()).isEqualTo(clientMail.getId());
    }

    @Test
    public void returnClientsWhenSearchingByCreateDateBetween()
    {
        // given
        final Address clientAddress = addressRepository.save(Address.builder().build());
        final Mail yesterdayNoonClientMail = mailRepository.save(Mail.builder().mail("yesterdayNoonClientMail@mail.com").build());
        final Mail todayNoonClientMail = mailRepository.save(Mail.builder().mail("todayNoonClientMail@mail.com").build());
        final Mail tomorrowNoonClientMail = mailRepository.save(Mail.builder().mail("tomorrowNoonClientMail@mail.com").build());

        final Client yesterdayNoonClient = repository.save(Client.builder()
                .address(clientAddress)
                .email(yesterdayNoonClientMail)
                .name("Test")
                .surname("Test")
                .createDate(valueOf(of(LocalDate.now().minusDays(1), NOON)))
                .managerId(1L)
                .build());
        final Client todayNoonClient = repository.save(Client.builder()
                .address(clientAddress)
                .email(todayNoonClientMail)
                .name("Test")
                .surname("Test")
                .createDate(valueOf(of(LocalDate.now(), NOON)))
                .managerId(1L)
                .build());
        final Client tomorrowNoonClient = repository.save(Client.builder()
                .address(clientAddress)
                .email(tomorrowNoonClientMail)
                .name("Test")
                .surname("Test")
                .createDate(valueOf(of(LocalDate.now().plusDays(1), NOON)))
                .managerId(1L)
                .build());

        final Timestamp yesterdayAtMidnight = valueOf(of(LocalDate.now().minusDays(1), MIDNIGHT));
        final Timestamp todayAtMidnight = valueOf(of(LocalDate.now(), MIDNIGHT));
        final Timestamp todayAtMax = valueOf(of(LocalDate.now(), MAX));
        final Timestamp tomorrowAtMax = valueOf(of(LocalDate.now().plusDays(1), MAX));

        // when
        List<Client> onlyTodayResult = repository.findClientByCreateDateBetween(todayAtMidnight, todayAtMax);
        List<Client> todayAndYesterdayResult = repository.findClientByCreateDateBetween(yesterdayAtMidnight, todayAtMax);
        List<Client> tomorrowAndYesterdayResult = repository.findClientByCreateDateBetween(yesterdayAtMidnight, tomorrowAtMax);

        // then
        assertThat(onlyTodayResult).isNotNull();
        assertThat(onlyTodayResult)
                .contains(todayNoonClient)
                .doesNotContain(yesterdayNoonClient, tomorrowNoonClient);

        assertThat(todayAndYesterdayResult).isNotNull();
        assertThat(todayAndYesterdayResult)
                .contains(todayNoonClient, yesterdayNoonClient)
                .doesNotContain(tomorrowNoonClient);

        assertThat(tomorrowAndYesterdayResult).isNotNull();
        assertThat(tomorrowAndYesterdayResult)
                .contains(todayNoonClient, yesterdayNoonClient, tomorrowNoonClient);
    }
}