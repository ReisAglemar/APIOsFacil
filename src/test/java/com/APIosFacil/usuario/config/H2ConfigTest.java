package com.APIosFacil.usuario.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("dev")
public class H2ConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void H2ConfigTeste() {
        assertNotNull(dataSource);

        DriverManagerDataSource driver = (DriverManagerDataSource) dataSource;
        assertNotNull(driver);

        assertEquals("jdbc:h2:mem:osFacilDb;DB_CLOSE_DELAY=-1", driver.getUrl());
        assertEquals("sa", driver.getUsername());
        assertEquals("", driver.getPassword());
    }
}
