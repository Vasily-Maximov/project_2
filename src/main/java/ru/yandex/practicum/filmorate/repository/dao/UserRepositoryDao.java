package ru.yandex.practicum.filmorate.repository.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Qualifier("userRepositoryDao")
@Slf4j
public class UserRepositoryDao extends AbstractRepository<User> implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User create(User user) {
        log.info("Создан пользователь {}", user);
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("person")
                .usingGeneratedKeyColumns("person_id");
        int id = simpleJdbcInsert.executeAndReturnKey(userToMap(user)).intValue();
        return findById(id);
    }

    @Override
    public User update(User user) {
        findById(user.getId());
        log.info("Пользователь обновлен {}", user);
        /*String sqlQuery = "UPDATE person " +
                          "SET email = ?, login = ?, name = ?, birthday = ? " +
                          "WHERE person_id = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, new String[]{"person_id"});
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setDate(4, Date.valueOf(user.getBirthday()));
            return preparedStatement;
        }, keyHolder);*/
        String sqlQuery = "UPDATE person SET email = ?, login = ?, name = ?, birthday = ? WHERE person_id = ?";
        jdbcTemplate.update(sqlQuery, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(), user.getId());
        return findById(user.getId());
    }

    @Override
    public Collection<User> getAll() {
        log.info("Выполнен запрос на получение пользователей");
        return jdbcTemplate.query("SELECT * FROM person", this::getRowMapperUser);
    }

    @Override
    public User findById(Integer id) {
        String sqlQuery = "SELECT * FROM person WHERE person_id = ?";
        try {
            log.info("Получен пользователь по id = {}", id);
            return jdbcTemplate.queryForObject(sqlQuery, getRowMapperUser(), id);
        } catch (DataAccessException e) {
            throw new ObjectNotFoundException(String.format("Пользователь с id = %d не найден", id));
        }
    }

    private RowMapper<User> getRowMapperUser() {
        return (rs, rowNum) -> {
            User user = new User(rs.getString("email"), rs.getString("login"),
                    rs.getString("name"), rs.getDate("birthday").toLocalDate());
            user.setId(rs.getInt("person_id"));
            return user;
        };
    }

    private User getRowMapperUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("email"), rs.getString("login"),
                rs.getString("name"), rs.getDate("birthday").toLocalDate());
        user.setId(rs.getInt("person_id"));
        return user;
    }

    private Map<String, String> userToMap(User user) {
        Map<String, String> users = new HashMap<>();
        users.put("email", user.getEmail());
        users.put("login", user.getLogin());
        users.put("name", user.getName());
        users.put("birthday", user.getBirthday().toString());
        return users;
    }
}