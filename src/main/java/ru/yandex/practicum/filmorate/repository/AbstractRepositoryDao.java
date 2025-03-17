package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.model.AbstractModel;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractRepositoryDao<T extends AbstractModel> {

    private final JdbcTemplate jdbcTemplate;

    public T create(T model) {
        return null;
    }

    public T update(T model) {
        return null;
    }

    public Collection<T> getAll() {
        return null;
    }

    public T findById(Integer id) {
        return null;
    }
}