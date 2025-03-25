package ru.yandex.practicum.filmorate.repository.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Component
@Slf4j
public class LikesRepositoryDao {

    private final JdbcTemplate jdbcTemplate;
    private final FilmRepository filmRepository;

    @Autowired
    public LikesRepositoryDao(@Qualifier("filmRepositoryDao") FilmRepository filmRepository, JdbcTemplate jdbcTemplate) {
        this.filmRepository = filmRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addLike(Film film, User user) {
        String sqlQuery = "INSERT INTO likes(film_id, person_id) " +
                "VALUES(?, ?)";
        jdbcTemplate.update(sqlQuery, film.getId(), user.getId());
    }

    public void delLike(Film film, User user) {
        String sqlQuery = "DELETE FROM likes WHERE film_id = ? AND person_id = ?";
        jdbcTemplate.update(sqlQuery, film.getId(), user.getId());
    }

    public List<Film> findPopularFilms(Integer count) {
        return filmRepository.getAll().stream()
                .sorted(Comparator.comparingInt(new ToIntFunction<Film>() {
                    @Override
                    public int applyAsInt(Film f) {
                        return -1 * f.getLikes().size();
                    }
                }))
                .limit(count)
                .sorted(new Comparator<Film>() {
                    @Override
                    public int compare(Film o1, Film o2) {
                        return o1.getId() - o2.getId();
                    }
                })
                .collect(Collectors.toList());
    }
}
