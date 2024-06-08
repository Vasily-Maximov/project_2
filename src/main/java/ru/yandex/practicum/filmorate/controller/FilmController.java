package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
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
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import java.util.Collection;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public Film createFilm(@Validated(CreateGroup.class) @RequestBody Film film) {
        log.info("Получен запрос на создание сущности {}", film);
        return filmService.create(film);
    }

    @PutMapping
    public Film updateFilm(@Validated(UpdateGroup.class) @RequestBody Film film) {
        log.info("Получен запрос на изменение сущности {}", film);
        return filmService.update(film);
    }

    @GetMapping
    public Collection<Film> getFilms() {
        log.info("Получен запрос на получение всех сущностей {}", ModelType.FILM);
        return filmService.getAll(ModelType.FILM);
    }
}