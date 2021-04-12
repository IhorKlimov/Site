package com.example.store.services;

import com.example.store.model.Movie;
import java.util.*;
import static java.util.Comparator.*;

public class MovieSorters {

    public static final Map<MovieSortCriteria, Comparator<Movie>> sorters;

    static {
        sorters = new HashMap<>();
        sorters.put(MovieSortCriteria.OLD_FIRST, comparing(Movie::getMovieId));
        sorters.put(MovieSortCriteria.NEW_FIRST, comparing(Movie::getMovieId, reverseOrder()));
        sorters.put(MovieSortCriteria.BY_TITLE, comparing(Movie::getTitle));
        sorters.put(MovieSortCriteria.BY_LIKES, comparing(Movie::getNumberOfLikes, reverseOrder()));
        sorters.put(MovieSortCriteria.BY_COMMENTS, comparing(Movie::getNumberOfComments, reverseOrder()));
    }
}
