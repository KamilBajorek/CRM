package com.bajorek_kalandyk.crm.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class ClientUpdateForm
{
    @NotNull
    private Long id;

    @JsonSerialize
    @NotNull
    @Email
    private String email;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String surname;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String street;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String city;

    @JsonSerialize
    @NotNull
    @Size(min = 6, max = 6)
    private String zipCode;

    @JsonSerialize
    @NotNull
    @Size(min = 1, max = 10)
    private String houseNumber;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String country;

}
