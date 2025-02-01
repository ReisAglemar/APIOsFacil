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
@ActiveProfiles("prod")
public class PostgreSqlConfigTest {

    @Autowired
    private DataSource dataSource;


    @Test
    public void PostgreSqlConfigTeste() {
        assertNotNull(dataSource);

        DriverManagerDataSource driver = (DriverManagerDataSource) dataSource;
        assertNotNull(driver);

        assertEquals(System.getenv("USUARIO_DB_USER"), driver.getUsername());
        assertEquals(System.getenv("USUARIO_DB_PASSWORD"), driver.getPassword());

    }

}