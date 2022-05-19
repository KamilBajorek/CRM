package com.bajorek_kalandyk.crm.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
@Table(name = "Users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long emailId;

    private String login;
    private String password;
    private String name;
    private String surname;
}