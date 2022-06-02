package com.bajorek_kalandyk.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DatabaseInitialize
{
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize()
    {
        try
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Clients(" +
                            "id INTEGER Primary key, " +
                            "name varchar(35) not null, " +
                            "surname varchar(35) not null," +
                            "emailId INTEGER not null," +
                            "managerId INTEGER not null," +
                            "addressId INTEGER not null)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Products(" +
                            "id INTEGER Primary key, " +
                            "name varchar(50) not null, " +
                            "description varchar(512) not null," +
                            "categoryId INTEGER not null," +
                            "price DECIMAL not null)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Users(" +
                            "id INTEGER Primary key, " +
                            "emailId INTEGER not null," +
                            "name varchar(50) not null, " +
                            "surname varchar(50) not null," +
                            "login varchar(50) not null, " +
                            "password varchar(128) not null)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Mails(" +
                            "id INTEGER Primary key, " +
                            "mail varchar(50) not null)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Addresses(" +
                            "id INTEGER Primary key, " +
                            "street varchar(64) not null," +
                            "city varchar(64) not null," +
                            "zipCode varchar(6) not null," +
                            "houseNumber varchar(10) not null," +
                            "country varchar(50) not null)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Purchases(" +
                            "id INTEGER Primary key, " +
                            "clientId INTEGER not null," +
                            "productId INTEGER not null," +
                            "date datetime not null)"
            );
            statement.close();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
