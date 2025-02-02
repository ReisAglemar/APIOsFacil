package com.APIosFacil.usuario.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("prod")
public class PostgreSqlConfigTest {

    @InjectMocks
    private DriverManagerDataSource driver;

    @Test
    @DisplayName("Deve verificar as configurações DB")
    public void TestaConfiguracaoPostgreSQL() {

        String DRIVER = "org.postgresql.Driver";
        String USUARIO_DB_LOCAL = System.getenv("USUARIO_DB_LOCAL");
        String USUARIO_DB_NAME = System.getenv("USUARIO_DB_NAME");
        String USUARIO_DB_USER = System.getenv("USUARIO_DB_USER");
        String USUARIO_DB_PASSWORD = System.getenv("USUARIO_DB_PASSWORD");

        driver.setDriverClassName(DRIVER);
        driver.setUrl(USUARIO_DB_LOCAL + "/" + USUARIO_DB_NAME);
        driver.setUsername(USUARIO_DB_USER);
        driver.setPassword(USUARIO_DB_PASSWORD);

        Assertions.assertNotNull(driver);
        assertEquals(USUARIO_DB_LOCAL + "/" +USUARIO_DB_NAME, driver.getUrl(), "Erro ao obter URL");
        assertEquals(USUARIO_DB_USER, driver.getUsername(), "Erro ao obter username ");
        assertEquals(USUARIO_DB_PASSWORD, driver.getPassword(), "Erro ao obter password");
    }
}
