package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.AbstractIntegrationTest;
import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ClientRestControllerIT extends AbstractIntegrationTest
{
    @Autowired
    private ObjectMapper objectMapper;

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
}