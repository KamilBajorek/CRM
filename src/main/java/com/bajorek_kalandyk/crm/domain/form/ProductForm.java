package com.bajorek_kalandyk.crm.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class ProductForm
{

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @JsonSerialize
    @NotNull
    @Size(min = 2, max = 128)
    private String description;

    @JsonSerialize
    @NotNull
    private BigDecimal price;

    @JsonSerialize
    @NotNull
    private Long categoryId;
}
