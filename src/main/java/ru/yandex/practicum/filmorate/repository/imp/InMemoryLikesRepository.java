package ru.yandex.practicum.filmorate.repository.imp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryLikesRepository {

    private final FilmRepository filmRepository;

    public InMemoryLikesRepository(@Qualifier("inMemoryFilmRepository") FilmRepository filmRepository) {

        this.filmRepository = filmRepository;
    }

    public void addLike(Film film, User user) {
        film.getLikes().add(user.getId());
    }

    public void delLike(Film film, User user) {
        film.getLikes().remove(user.getId());
    }

    public List<Film> findPopularFilms(Integer count) {
        return filmRepository.getAll().stream()
                .sorted(Comparator.comparingInt(f -> -1 * f.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }
}
