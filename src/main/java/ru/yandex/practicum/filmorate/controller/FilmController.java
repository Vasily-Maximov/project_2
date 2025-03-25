package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;

import java.util.Collection;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController extends AbstractController<Film> {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        super(filmService);
        this.filmService = filmService;
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable(value = "id") Integer filmId, @PathVariable Integer userId) {
        log.info("Передан запрос на добавление лайка пользователя с id = {} фильму с id = {}", userId, filmId);
        filmService.addLike(filmId, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void delLike(@PathVariable(value = "id") Integer filmId, @PathVariable Integer userId) {
        log.info("Передан запрос на удаление лайка пользователя с id = {} фильму с id = {}", userId, filmId);
        filmService.delLike(filmId, userId);
    }

    @GetMapping("/popular")
    public Collection<Film> findPopularFilms(@RequestParam(defaultValue = "10") Integer count) {
        log.info("Передан запрос на получение популярных фильмов по количеству лайков");
        return filmService.findPopularFilms(count);
    }
}