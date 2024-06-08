package ru.yandex.practicum.filmorate.repository.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import ru.yandex.practicum.filmorate.repository.UserRepository;

@Repository
public class InMemoryUserRepository extends AbstractRepository<User> implements UserRepository {
}