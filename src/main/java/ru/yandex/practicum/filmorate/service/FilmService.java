package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.AbstractRepositoryDao;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService extends AbstractService<Film> {

    private final UserRepository userRepository;

    @Autowired
    public FilmService(AbstractRepositoryDao<Film> abstractRepositoryDao, UserRepository userRepository) {
        super(abstractRepositoryDao);
        this.userRepository = userRepository;
    }

    public void addLike(Integer filmId, Integer userId) {
        Film film = super.findById(filmId);
        userRepository.findById(userId);
        film.getLikes().add(userId);
    }

    public void delLike(Integer filmId, Integer userId) {
        Film film = super.findById(filmId);
        userRepository.findById(userId);
        film.getLikes().remove(userId);
    }

    public List<Film> findPopularFilms(Integer count) {
        return super.getAll().stream()
                .sorted(Comparator.comparingInt(f -> -1 * f.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }
}