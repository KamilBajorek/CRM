package com.bajorek_kalandyk.crm.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class UserForm
{
    @JsonSerialize
    @NotNull
    @Size(min = 3, max = 50)
    private final String login;

    @JsonSerialize
    @NotNull
    @Email
    private final String email;

    @JsonSerialize
    @NotNull
    @Size(min = 3, max = 50)
    private final String password;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String name;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String surname;
}
