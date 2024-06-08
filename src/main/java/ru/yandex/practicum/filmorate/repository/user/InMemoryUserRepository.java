package ru.yandex.practicum.filmorate.repository.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import java.util.Collection;

@Repository
public class InMemoryUserRepository extends AbstractRepository<User> implements UserRepository {

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
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}