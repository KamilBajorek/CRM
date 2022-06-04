package com.bajorek_kalandyk.crm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
@Table(name = "Clients")
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long managerId;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToOne
    @JoinColumn(name = "emailId")
    private Mail email;

    private String name;
    private String surname;

    private Timestamp createDate;
}
