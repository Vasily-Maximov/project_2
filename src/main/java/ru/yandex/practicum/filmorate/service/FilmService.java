package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import ru.yandex.practicum.filmorate.repository.imp.InMemoryFilmRepository;
import ru.yandex.practicum.filmorate.repository.imp.InMemoryLikesRepository;
import java.util.List;

@Service
public class FilmService extends AbstractService<Film> {

    private final InMemoryLikesRepository likesRepository;
    private final UserRepository userRepository;

    @Autowired
    public FilmService(InMemoryFilmRepository filmRepository, InMemoryLikesRepository likesRepository, UserRepository userRepository) {
        super(filmRepository);
        this.likesRepository = likesRepository;
        this.userRepository = userRepository;
    }

    public void addLike(Integer filmId, Integer userId) {
        Film film = super.findById(filmId);
        User user = userRepository.findById(userId);
        likesRepository.addLike(film, user);
    }

    public void delLike(Integer filmId, Integer userId) {
        Film film = super.findById(filmId);
        User user = userRepository.findById(userId);
        likesRepository.delLike(film, user);
    }

    public List<Film> findPopularFilms(Integer count) {
        return likesRepository.findPopularFilms(count);
    }
}