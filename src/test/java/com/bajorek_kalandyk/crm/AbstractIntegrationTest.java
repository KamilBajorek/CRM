package com.bajorek_kalandyk.crm;

import com.bajorek_kalandyk.crm.config.DatabaseInitialize;
import com.bajorek_kalandyk.crm.domain.UserAccountDetails;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest
{
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    private static final User principal = User.builder()
            .id(1L)
            .login("ADMIN")
            .password("XXX")
            .name("ADMIN")
            .surname("ADMIN")
            .email(Mail.builder().mail("admin@admin.pl").build())
            .build();

    @Before
    public void setUp()
    {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(true);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(authentication.getPrincipal()).thenReturn(new UserAccountDetails(principal));
    }

    @After
    public void clearDatabase() throws SQLException
    {
        final List<String> tables = DatabaseInitialize.TABLES;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        for (String table : tables)
        {
            statement.execute(String.format("DELETE FROM %s", table));
        }
        statement.close();
        connection.close();
    }
}
