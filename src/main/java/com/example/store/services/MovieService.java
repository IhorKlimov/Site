package com.example.store.services;

import com.example.store.model.*;
import java.util.Collection;

public interface MovieService {

    Collection<Movie> getAllMovies();

    Collection<Movie> getAllMovies(MovieSortCriteria movieSortCriteria);

    Collection<Movie> search(String text);

    Collection<Movie> search(String text, MovieSortCriteria movieSortCriteria);

    Movie getMovieById(Integer movieId);

    void likeMovie(Movie movie, User user);

    void unlikeMovie(Movie movie, User user);

    void addComment(Movie movie, User user, String comment);
}
