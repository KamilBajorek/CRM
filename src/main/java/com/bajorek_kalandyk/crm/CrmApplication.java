package com.bajorek_kalandyk.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CrmApplication
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args)
    {
        SpringApplication.run(CrmApplication.class, args);
    }
}
