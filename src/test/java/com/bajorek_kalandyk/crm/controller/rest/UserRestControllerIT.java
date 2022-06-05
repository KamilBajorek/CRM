package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.AbstractIntegrationTest;
import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserRestControllerIT extends AbstractIntegrationTest
{
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void properlyCreateUserWhenUsingRestController() throws Exception
    {
        // given
        final UserForm userForm = UserForm.builder()
                .login("IT_TEST1")
                .password("IT_TEST")
                .name("IT_TEST")
                .surname("IT_TEST")
                .email("IT_TEST1@TEST.COM")
                .build();

        // when
        mockMvc.perform(post("/user/create")
                .content(objectMapper.writeValueAsString(userForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        User userEntity = userRepository.findByLogin("IT_TEST1");
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getEmail().getMail()).isEqualTo("IT_TEST1@TEST.COM");
    }
}