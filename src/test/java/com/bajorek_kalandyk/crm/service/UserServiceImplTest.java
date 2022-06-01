package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.exception.UserAlreadyExistsException;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest
{
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MailService mailService;

    @Mock
    private MailRepository mailRepository;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    private static final User TEST_USER_1 = User.builder().id(1L).name("User").build();

    @Before
    public void setUp()
    {
        when(repository.findAll()).thenReturn(singletonList(TEST_USER_1));
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(TEST_USER_1));
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
        assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void returnExistingUserWhenCallingGetMethod()
    {
        // when
        final Optional<User> result = userService.getById(1L);

        // then
        assertTrue(result.isPresent());
        assertThat(result.get()).isEqualTo(TEST_USER_1);
    }

    @Test
    public void returnEmptyUserWhenCallingGetMethodAndUserWithGivenIdDoesNotExist()
    {
        // when
        final Optional<User> result = userService.getById(12000L);

        // then
        assertFalse(result.isPresent());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void throwExceptionWhenTryingToCreateUserWithAlreadyExistingMail() throws UserAlreadyExistsException
    {
        // given
        final String existingMailAddress = "ExistingEmail";
        final String login = "Test";
        final Mail existingMail = Mail.builder().build();

        when(repository.findByLogin(login)).thenReturn(null);
        when(mailRepository.findMailByMail(existingMailAddress)).thenReturn(existingMail);
        when(repository.findByEmail(existingMail)).thenReturn(TEST_USER_1);

        final UserForm userForm = UserForm.builder()
                .email(existingMailAddress)
                .login(login)
                .name("Test")
                .password("Test")
                .surname("Test")
                .build();

        // when
        userService.createUser(userForm);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void throwExceptionWhenTryingToCreateUserWithAlreadyExistingLogin() throws UserAlreadyExistsException
    {
        // given
        final String mailAddress = "Email";
        final String login = "Test";

        when(repository.findByLogin(login)).thenReturn(TEST_USER_1);
        when(mailRepository.findMailByMail(mailAddress)).thenReturn(null);

        final UserForm userForm = UserForm.builder()
                .email(mailAddress)
                .login(login)
                .name("Test")
                .password("Test")
                .surname("Test")
                .build();

        // when
        userService.createUser(userForm);
    }

    @Test
    public void returnProperlyConvertedUserFromUserFormWhenCreatingUser() throws UserAlreadyExistsException
    {
        // given
        final String mailAddress = "Email";
        final String login = "Test";
        final String passwordToEncode = "passwordToEncode";
        final String encodedPassword = "encodedPassword";
        final Mail createdMail = Mail.builder().build();

        final UserForm userForm = UserForm.builder()
                .email(mailAddress)
                .login(login)
                .name("Test")
                .password(passwordToEncode)
                .surname("Test")
                .build();

        final User expected = User.builder()
                .login(login)
                .name("Test")
                .surname("Test")
                .password(encodedPassword)
                .email(createdMail)
                .build();

        when(repository.findByLogin(login)).thenReturn(null);
        when(mailRepository.findMailByMail(mailAddress)).thenReturn(null);
        when(mailService.createMail(mailAddress)).thenReturn(createdMail);
        when(passwordEncoder.encode(passwordToEncode)).thenReturn(encodedPassword);
        when(repository.save(expected)).thenReturn(expected);

        // when
        final User result = userService.createUser(userForm);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
    }
}