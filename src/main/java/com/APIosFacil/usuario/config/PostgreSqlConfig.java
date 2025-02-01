package com.APIosFacil.usuario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class PostgreSqlConfig {

    private String DRIVER = "org.postgresql.Driver";

    @Value("${USUARIO_DB_LOCAL}")
    private String USUARIO_DB_LOCAL;

    @Value("${USUARIO_DB_NAME}")
    private String USUARIO_DB_NAME;

    @Value("${USUARIO_DB_USER}")
    private String USUARIO_DB_USER;

    @Value("${USUARIO_DB_PASSWORD}")
    private String USUARIO_DB_PASSWORD;

    @Bean
    public DataSource dataSource() {
        String URL = "jdbc:postgresql://" + USUARIO_DB_LOCAL + "/" + USUARIO_DB_NAME;
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USUARIO_DB_USER);
        dataSource.setPassword(USUARIO_DB_PASSWORD);
        return dataSource;
    }


}