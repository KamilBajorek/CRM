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
public class ClientForm
{

    @JsonSerialize
    @NotNull
    @Email
    private final String email;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String name;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String surname;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String street;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String city;

    @JsonSerialize
    @NotNull
    @Size(min = 6, max = 6)
    private final String zipCode;

    @JsonSerialize
    @NotNull
    @Size(min = 1, max = 10)
    private final String houseNumber;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private final String country;
}
