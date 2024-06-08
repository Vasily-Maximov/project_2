package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ModelType;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import java.util.Collection;

@Service
public class FilmService extends AbstractService<Film> {

    @Autowired
    public FilmService(AbstractRepository<Film> abstractRepository) {
        super(abstractRepository);
    }

    public Film create(Film film) {
        return super.create(film);
    }

    public Film update(Film film) {
        return super.update(film);
    }

    public Collection<Film> getAll(ModelType modelType) {
        return super.getAll(modelType);
    }
}