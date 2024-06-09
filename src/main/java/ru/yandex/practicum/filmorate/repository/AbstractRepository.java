package ru.yandex.practicum.filmorate.repository;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exeption.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.AbstractModel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        findById(model.getId());
        log.info("Сущность обновлена {}", model);
        data.put(model.getId(), model);
        return model;
    }

    public Collection<T> getAll() {
        log.info("Выполнен запрос на получение сущностей");
        return data.values();
    }

    public T findById(int id) {
        if (data.containsKey(id)) {
            log.info("Получена сущность по id = {}", id);
            return data.get(id);
        } else {
            String message = String.format("Сущность по id = %d не найдена", id);
            log.error(message);
            throw new ObjectNotFoundException(message);
        }
    }
}