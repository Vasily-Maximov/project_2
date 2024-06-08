package ru.yandex.practicum.filmorate.repository.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import ru.yandex.practicum.filmorate.repository.FilmRepository;

@Repository
public class InMemoryFilmRepository extends AbstractRepository<Film> implements FilmRepository {

}