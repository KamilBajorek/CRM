package com.bajorek_kalandyk.crm.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class UserForm
{
    @JsonSerialize
    @NotNull
    @Size(min = 3, max = 50)
    private String login;

    @JsonSerialize
    @NotNull
    @Email
    private String email;

    @JsonSerialize
    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String surname;
}
