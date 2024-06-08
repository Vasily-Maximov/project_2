package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.ModelType;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import java.util.Collection;

@Service
public class UserService extends AbstractService<User> {

    @Autowired
    public UserService(AbstractRepository<User> abstractRepository) {
        super(abstractRepository);
    }

    public User create(User user) {
        return super.create(user);
    }

    public User update(User user) {
        return super.update(user);
    }

    public Collection<User> getAll(ModelType modelType) {
        return super.getAll(modelType);
    }
}