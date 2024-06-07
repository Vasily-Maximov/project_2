package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ModelType;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import java.util.Collection;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController extends AbstractController<Film> {

    @PostMapping
    public Film createFilm(@Validated(CreateGroup.class) @RequestBody Film film) {
        log.info("Получен запрос на создание сущности {}", film);
        return super.create(film);
    }

    @PutMapping
    public Film updateFilm(@Validated(UpdateGroup.class) @RequestBody Film film) {
        log.info("Получен запрос на изменение сущности {}", film);
        return super.update(film);
    }

    @GetMapping
    public Collection<Film> getFilms() {
        log.info("Получен запрос на получение всех сущностей {}", ModelType.FILM);
        return super.getAll(ModelType.FILM);
    }
}