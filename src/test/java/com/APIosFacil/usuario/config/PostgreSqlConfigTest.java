package com.APIosFacil.usuario.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("prod")
public class PostgreSqlConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void PostgreSqlConfigTeste() {
        PostgreSqlConfig postgreSqlConfig = new PostgreSqlConfig();

        ReflectionTestUtils.setField(postgreSqlConfig, "USUARIO_DB_LOCAL", "localhost:5432");
        ReflectionTestUtils.setField(postgreSqlConfig, "USUARIO_DB_NAME", "usuariodb");
        ReflectionTestUtils.setField(postgreSqlConfig, "USUARIO_DB_USER", "user");
        ReflectionTestUtils.setField(postgreSqlConfig, "USUARIO_DB_PASSWORD", "password");

        DataSource dataSource = postgreSqlConfig.dataSource();
        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource) dataSource;

        assertNotNull(driverManagerDataSource);
        assertEquals("jdbc:postgresql://localhost:5432/usuariodb", driverManagerDataSource.getUrl(), "Erro ao obter url");
        assertEquals("user", driverManagerDataSource.getUsername(), "Erro ao obter user");
        assertEquals("password", driverManagerDataSource.getPassword(), "Erro ao obter password");
    }

    @Test
    public void PostgreSqlConexaoTeste() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Conexão não pode ser nula");
            assertTrue(connection.isValid(5), "Conexão foi inválida");
        }
    }
}
