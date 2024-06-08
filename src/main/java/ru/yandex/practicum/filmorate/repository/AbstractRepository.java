package ru.yandex.practicum.filmorate.repository;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exeption.ObjectValidationException;
import ru.yandex.practicum.filmorate.model.AbstractModel;
import ru.yandex.practicum.filmorate.model.ModelType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractRepository<T extends AbstractModel> {

    private final Map<Integer, T> data = new HashMap<>();
    private int globalId = 0;

    private void getNextId() {
        ++globalId;
    }

    public T create(T model) {
        getNextId();
        model.setId(globalId);
        log.info("Создана сущность {}", model);
        data.put(globalId, model);
        return model;
    }

    public T update(T model) {
        if (data.containsKey(model.getId())) {
            log.info("Сущность обновлена {}", model);
            model.setModelType(data.get(model.getId()).getModelType());
            data.put(model.getId(), model);
            return model;
        } else {
            String message = String.format("Сущность по id = %d не найдена", model.getId());
            log.error(message);
            throw new ObjectValidationException(message);
        }
    }

    public Collection<T> getAll(ModelType modelType) {
        log.info("Получение всех сущностей {}", modelType);
        return data.values().stream().filter(model -> model.getModelType().equals(modelType)).collect(Collectors.toList());
    }
}