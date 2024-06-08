package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.AbstractModel;
import ru.yandex.practicum.filmorate.model.ModelType;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import java.util.Collection;

@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractModel> {

    private final AbstractRepository<T> abstractRepository;

    public T create(T model) {
        return abstractRepository.create(model);
    }

    public T update(T model) {
        return abstractRepository.update(model);
    }

    public Collection<T> getAll(ModelType modelType) {
        return abstractRepository.getAll(modelType);
    }
}