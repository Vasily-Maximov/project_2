package ru.yandex.practicum.filmorate.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.AbstractRepositoryDao;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import java.util.Collection;

@Component
public class UserRepositoryDao extends AbstractRepositoryDao<User> implements UserRepository {

    @Autowired
    public UserRepositoryDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public Collection<User> getAll() {
        return super.getAll();
    }

    @Override
    public User findById(Integer id) {
        return super.findById(id);
    }
}