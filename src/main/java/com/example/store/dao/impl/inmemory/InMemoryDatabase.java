package com.example.store.dao.impl.inmemory;

import com.example.store.dao.DaoFactory;
import com.example.store.model.*;
import java.util.*;

public class InMemoryDatabase {

    Map<Integer, User> users;
    Map<Integer, Category> categories;
    Map<Integer, Item> items;

    public InMemoryDatabase() {
        users = new TreeMap<>();
        categories = new TreeMap<>();
        items = new TreeMap<>();
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }

}
