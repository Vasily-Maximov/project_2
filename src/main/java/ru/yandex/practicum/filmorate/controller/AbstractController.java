package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.AbstractModel;
import ru.yandex.practicum.filmorate.service.AbstractService;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public class AbstractController<T extends AbstractModel> {

    private final AbstractService<T> abstractService;

    @PostMapping
    public T create(@Validated(CreateGroup.class) @RequestBody T model) {
        log.info("Получен запрос на создание сущности {}", model);
        return abstractService.create(model);
    }

    @PutMapping
    public T update(@Validated(UpdateGroup.class) @RequestBody T model) {
        log.info("Получен запрос на изменение сущности {}", model);
        return abstractService.update(model);
    }

    @GetMapping
    public Collection<T> getAll() {
        log.info("Получен запрос на получение сущностей");
        return abstractService.getAll();
    }

    @GetMapping("/{id}")
    public T findById(@PathVariable("id") int id) {
        log.info("Получен запрос на получение сущности по id = {}", id);
        return abstractService.findById(id);
    }
}