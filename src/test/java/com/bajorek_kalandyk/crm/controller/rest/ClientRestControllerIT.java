package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.AbstractIntegrationTest;
import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.form.ClientUpdateForm;
import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.repository.AddressRepository;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

public class ClientRestControllerIT extends AbstractIntegrationTest
{
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void properlyCreateClientWhenUsingRestController() throws Exception
    {
        // given
        final ClientForm userForm = ClientForm.builder()
                .name("TEST_NAME")
                .surname("TEST_SURNAME")
                .email("IT_TEST@TEST.COM")
                .street("TEST_STREET")
                .city("TEST_CITY")
                .zipCode("00-000")
                .houseNumber("12")
                .country("TEST_COUNTRY")
                .build();
        // when
        mockMvc.perform(post("/client/create")
                .content(objectMapper.writeValueAsString(userForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        Mail mailEntity = mailRepository.findMailByMail("IT_TEST@TEST.COM");
        assertThat(mailEntity).isNotNull();
        Client clientEntity = clientRepository.findClientByEmail(mailEntity);
        assertThat(clientEntity).isNotNull();
        assertThat(clientEntity.getAddress()).isNotNull();
        assertThat(clientEntity.getName()).isEqualTo("TEST_NAME");
    }

    @Test
    public void properlyUpdateClientWhenUsingRestController() throws Exception
    {
        // given
        final Address clientAddress = addressRepository.save(Address.builder()
                .street("TEST_STREET")
                .city("TEST_CITY")
                .zipCode("00-000")
                .houseNumber("12")
                .country("TEST_COUNTRY")
                .build());
        final Mail clientMail = mailRepository.save(Mail.builder().mail("IT_TEST@TEST.COM").build());
        final Client clientToUpdate = Client.builder()
                .name("TEST_NAME")
                .surname("TEST_SURNAME")
                .createDate(valueOf(now()))
                .managerId(1L)
                .email(clientMail)
                .address(clientAddress)
                .build();
        clientRepository.save(clientToUpdate);

        final ClientUpdateForm userForm = ClientUpdateForm.builder()
                .id(clientToUpdate.getId())
                .name("NEW_NAME")
                .surname("NEW_SURNAME")
                .email("NEW@TEST.COM")
                .street("NEW_STREET")
                .city("NEW_CITY")
                .zipCode("01-101")
                .houseNumber("10")
                .country("NEW_COUNTRY")
                .build();
        // when
        mockMvc.perform(put("/client/update")
                .content(objectMapper.writeValueAsString(userForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        Mail oldClientMail = mailRepository.findMailByMail("IT_TEST@TEST.COM");
        Mail currentClientMail = mailRepository.findMailByMail("NEW@TEST.COM");
        assertThat(oldClientMail).isNotNull();
        assertThat(currentClientMail).isNotNull();

        Client oldClient = clientRepository.findClientByEmail(oldClientMail);
        Client currentClient = clientRepository.findClientByEmail(currentClientMail);

        assertThat(oldClient).isNull();
        assertThat(currentClient).isNotNull();
        assertThat(currentClient.getAddress()).isNotNull();
        assertThat(currentClient.getName()).isEqualTo("NEW_NAME");
        assertThat(currentClient.getId()).isEqualTo(clientToUpdate.getId());
    }
}