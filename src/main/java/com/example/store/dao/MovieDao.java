package com.example.store.dao;

import com.example.store.model.*;
import java.util.Collection;

public interface MovieDao extends AbstractDao<Movie> {

    void like(Movie movie, User user);

    void unlike(Movie movie, User user);

    Collection<Movie> findByText(String text);
}
