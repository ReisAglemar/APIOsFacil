package com.APIosFacil.usuario.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
public class H2ConfigTest {

    @InjectMocks
    public DriverManagerDataSource driver;

    @Test
    @DisplayName("Deve verificar as configurações DB")
    public void TestaConfiguracaoH2() {
        String URL = "jdbc:h2:mem:osFacilDb;DB_CLOSE_DELAY=-1";
        String USERNAME = "sa";
        String PASSWORD = "";

        driver.setUrl(URL);
        driver.setUsername(USERNAME);
        driver.setPassword(PASSWORD);

        assertNotNull(driver);
        assertEquals(URL, driver.getUrl(), "Erro ao obter URL ");
        assertEquals(USERNAME, driver.getUsername(), "Erro ao obter username ");
        assertEquals(PASSWORD, driver.getPassword(), "Erro ao obter password");
    }
}
