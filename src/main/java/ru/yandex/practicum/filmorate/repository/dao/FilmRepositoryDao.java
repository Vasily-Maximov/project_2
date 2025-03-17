package ru.yandex.practicum.filmorate.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.AbstractRepositoryDao;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import java.util.Collection;

@Component
public class FilmRepositoryDao extends AbstractRepositoryDao<Film> implements FilmRepository {

    @Autowired
    public FilmRepositoryDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Film create(Film film) {
        return super.create(film);
    }

    @Override
    public Film update(Film film) {
        return super.update(film);
    }

    @Override
    public Collection<Film> getAll() {
        return super.getAll();
    }

    @Override
    public Film findById(Integer id) {
        return super.findById(id);
    }
}