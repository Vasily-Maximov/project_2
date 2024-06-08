package ru.yandex.practicum.filmorate.repository.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ModelType;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import java.util.Collection;

@Repository
public class InMemoryFilmRepository extends AbstractRepository<Film> implements FilmRepository {

   @Override
   public Film create(Film film) {
       return super.create(film);
   }

   @Override
   public Film update(Film film) {
       return super.update(film);
   }

    @Override
    public Collection<Film> getAll() {
        return super.getAll(ModelType.FILM);
    }

    @Override
    public Film findById(Integer id) {
        return null;
    }
}