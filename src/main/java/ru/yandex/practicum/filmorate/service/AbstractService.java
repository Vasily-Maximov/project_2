package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.AbstractModel;
import ru.yandex.practicum.filmorate.repository.AbstractRepositoryDao;
import java.util.Collection;

@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractModel> {

    private final AbstractRepositoryDao<T> abstractRepositoryDao;

    public T create(T model) {
        return abstractRepositoryDao.create(model);
    }

    public T update(T model) {
        return abstractRepositoryDao.update(model);
    }

    public Collection<T> getAll() {
        return abstractRepositoryDao.getAll();
    }
    public T findById(int id) {
        return abstractRepositoryDao.findById(id);
    }
}