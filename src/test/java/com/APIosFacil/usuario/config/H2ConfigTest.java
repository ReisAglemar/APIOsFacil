package com.APIosFacil.usuario.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals("jdbc:h2:mem:osFacilDb;DB_CLOSE_DELAY=-1", driver.getUrl(), "Erro ao obter URL ");
        assertEquals("sa", driver.getUsername(), "Erro ao obter username ");
        assertEquals("", driver.getPassword(), "Erro ao obter password");
    }

    @Test
    public void H2ConexaoTeste() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Não pode ser nulo");
            assertTrue(connection.isValid(5), "Conexão foi inválida");
        }
    }
}
