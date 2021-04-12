package com.example.store.dao.impl.inmemory;

import com.example.store.dao.DaoFactory;
import com.example.store.model.*;
import java.util.*;

public class InMemoryDatabase {

    Map<Integer, Movie> movies;
    Map<Integer, Category> categories;
    Map<Integer, Comment> comments;
    Map<Integer, User> users;

    public InMemoryDatabase() {
        movies = new TreeMap<>();
        comments = new TreeMap<>();
        users = new TreeMap<>();
        categories = new TreeMap<>();
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }

}
