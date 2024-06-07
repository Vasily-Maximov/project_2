package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.filmorate.model.ModelType;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController extends AbstractController<User> {

    @PostMapping
    public User createUser(@Validated(CreateGroup.class) @RequestBody User user) {
        log.info("Получен запрос на создание сущности {}", user);
        return super.create(user);
    }

    @PutMapping
    public User updateUser(@Validated(UpdateGroup.class) @RequestBody User user) {
        log.info("Получен запрос на изменение сущности {}", user);
        return super.update(user);
    }

    @GetMapping
    public Collection<User> getUsers() {
        log.info("Получен запрос на получение всех сущностей {}", ModelType.USER);
        return super.getAll(ModelType.USER);
    }
}