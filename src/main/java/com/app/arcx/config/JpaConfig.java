package com.app.arcx.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ConfigurationProperties(prefix = "params.datasource")
public class JpaConfig extends HikariConfig {

    @Bean
    public DataSource dataSource() throws SQLException {

        HikariConfig hikari = new HikariConfig();
        hikari.setJdbcUrl(getDataSourceUrl());
        hikari.setPoolName("Hikari");
        hikari.setAutoCommit(false);
        hikari.setUsername(System.getenv("DB_USERNAME"));
        hikari.setPassword(System.getenv("DB_PASSWORD"));

        return new HikariDataSource(hikari);
    }

    private String getDataSourceUrl() {
        return "jdbc:postgresql://"
                + System.getenv("DB_HOST") + "/"
                + System.getenv("DB_NAME")
                + "?currentSchema=data";
    }

}



