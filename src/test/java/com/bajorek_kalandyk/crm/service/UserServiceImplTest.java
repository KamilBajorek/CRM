package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceImplTest
{
    @Mock
    private MailRepository mailRepository;

    @Mock
    private UserRepository repository;

    @Mock
    @InjectMocks
    private UserServiceImpl userService;

    private static final User TEST_USER_1 = User.builder().id(1L).name("User").build();

    @Before
    public void setUp()
    {
        when(repository.findAll()).thenReturn(singletonList(TEST_USER_1));
    }

    @Test
    public void returnEmptyListOfUsersWhenCallingGetAllMethodAndRepositoryReturnsEmpty()
    {
        // given
        when(repository.findAll()).thenReturn(emptyList());
        // when
        final List<User> result = userService.getAll();
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void returnListOfUsersWhenCallingGetAllMethod()
    {
        // when
        final List<User> result = userService.getAll();
        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
    }
}