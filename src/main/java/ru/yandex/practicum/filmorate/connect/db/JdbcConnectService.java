package ru.yandex.practicum.filmorate.connect.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


@Component
public class JdbcConnectService {

    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/filmorate";
    public static final String JDBC_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    public static final String JDBC_USER_NAME = "postgres";
    public static final String JDBC_PASSWORD = "iamroot";

    public JdbcTemplate getTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setDriverClassName(JDBC_DRIVER_CLASS_NAME);
        dataSource.setUsername(JDBC_USER_NAME);
        dataSource.setPassword(JDBC_PASSWORD);
        return new JdbcTemplate(dataSource);
    }
}