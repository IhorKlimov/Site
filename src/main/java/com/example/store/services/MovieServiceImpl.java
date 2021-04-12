package com.example.store.services;

import com.example.store.dao.DaoFactory;
import com.example.store.model.Movie;
import com.example.store.model.User;
import java.util.Collection;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    DaoFactory daoFactory;

    public MovieServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        return daoFactory.getMovieDao().get(movieId);
    }

    @Override
    public void likeMovie(Movie movie, User user) {
        daoFactory.getMovieDao().like(movie, user);
    }

    @Override
    public void unlikeMovie(Movie movie, User user) {
        daoFactory.getMovieDao().unlike(movie, user);
    }

    @Override
    public void addComment(Movie movie, User user, String comment) {
        daoFactory.getCommentDao().addComment(movie, user, comment);
    }

    @Override
    public Collection<Movie> getAllMovies() {
        return daoFactory.getMovieDao().findAll();
    }

    @Override
    public Collection<Movie> getAllMovies(MovieSortCriteria movieSortCriteria) {
        Collection<Movie> movies = getAllMovies();
        return sort(movies, movieSortCriteria);
    }

    @Override
    public Collection<Movie> search(String text, MovieSortCriteria movieSortCriteria) {
        Collection<Movie> movies = search(text);
        return sort(movies, movieSortCriteria);
    }

    @Override
    public Collection<Movie> search(String text) {
        if (text == null || text.equals("")) {
            return getAllMovies();
        }
        return daoFactory.getMovieDao().findByText(text);
    }

    private Collection<Movie> sort(Collection<Movie> movies, MovieSortCriteria movieSortCriteria) {
        return movies.stream()
                .sorted(MovieSorters.sorters.get(movieSortCriteria))
                .collect(Collectors.toList());
    }

}
