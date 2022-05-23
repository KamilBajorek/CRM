package com.bajorek_kalandyk.crm.domain.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToOne
    @JoinColumn(name = "emailId")
    private Mail email;

    private String login;
    private String password;
    private String name;
    private String surname;
}