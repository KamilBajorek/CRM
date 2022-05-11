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
            statement.close();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
