package com.example.store.dao.impl.inmemory;

import com.example.store.dao.MovieDao;
import com.example.store.model.*;
import java.util.Collection;
import java.util.stream.*;

class InMemoryMovieDao extends InMemoryAbstractDao<Movie> implements MovieDao {

    InMemoryMovieDao(InMemoryDatabase database) {
        super(database.movies, Movie::getMovieId, Movie::setMovieId, database);
    }

    @Override
    public void like(Movie movie, User user) {
        movie.getLikers().add(user);
    }

    @Override
    public void unlike(Movie movie, User user) {
        movie.getLikers().remove(user);
    }

    @Override
    public Collection<Movie> findByText(String text) {
        String[] words = text.toLowerCase().split(" ");
        return database.movies.values().stream()
                .filter(movie -> containsAllWords(movie, words))
                .collect(Collectors.toList());
    }

    private static boolean containsAllWords(Movie movie, String[] words) {
        String string = movie.getTitle() + " " + movie.getDescription();
        string = string.toLowerCase();
        return Stream.of(words).allMatch(string::contains);
    }

}
