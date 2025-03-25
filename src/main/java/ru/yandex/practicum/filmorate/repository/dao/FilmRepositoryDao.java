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
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Qualifier("filmRepositoryDao")
@Slf4j
public class FilmRepositoryDao extends AbstractRepository<Film> implements FilmRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Film create(Film film) {
        log.info("Фильм создан {}", film);
        String sqlQuery = "INSERT INTO film(name, description, release_date, duration) " +
                          "VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, new String[]{"film_id"});
            preparedStatement.setString(1, film.getName());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setDate(3, Date.valueOf(film.getReleaseDate()));
            preparedStatement.setLong(4, film.getDuration());
            return preparedStatement;
        }, keyHolder);

        return findById(id);
    }

    @Override
    public Film update(Film film) {
        findById(film.getId());
        log.info("Фильм обновлен {}", film);
        /*SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("film")
                .usingGeneratedKeyColumns("film_id");
        int id = simpleJdbcInsert.executeAndReturnKey(filmToMap(film)).intValue();*/
        String sqlQuery = "UPDATE film SET name = ?, description = ?, release_date = ?, duration = ? WHERE film_id = ?";
        jdbcTemplate.update(sqlQuery, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(), film.getId());
        return findById(film.getId());
    }

    @Override
    public Collection<Film> getAll() {
        log.info("Выполнен запрос на получение фильмов");
        return jdbcTemplate.query("SELECT * FROM film", this::getRowMapperFilm);
    }

    @Override
    public Film findById(Integer id) {
        String sqlQuery = "SELECT * FROM film WHERE film_id = ?";
        try {
            log.info("Получен фильм по id = {}", id);
            return jdbcTemplate.queryForObject(sqlQuery, getRowMapperFilm(), id);
        } catch (DataAccessException e) {
            throw new ObjectNotFoundException(String.format("Фильм с id = %d не найден", id));
        }
    }

    private RowMapper<Film> getRowMapperFilm() {
        return (rs, rowNum) -> {
            Film film = new Film(rs.getString("name"), rs.getString("description"),
                    rs.getDate("release_date").toLocalDate(), rs.getLong("duration"));
            film.setId(rs.getInt("film_id"));
            return film;
        };
    }

    private Film getRowMapperFilm(ResultSet rs, int rowNum) throws SQLException {
        Film film = new Film(rs.getString("name"), rs.getString("description"),
                rs.getDate("release_date").toLocalDate(), rs.getLong("duration"));
        film.setId(rs.getInt("film_id"));
        return film;
    }

    private Map<String, String> filmToMap(Film film) {
        Map<String, String> films = new HashMap<>();
        films.put("name", film.getName());
        films.put("description", film.getDescription());
        films.put("release_date", film.getReleaseDate().toString());
        films.put("duration", String.valueOf(film.getDuration()));
        return films;
    }
}