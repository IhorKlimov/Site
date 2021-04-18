package com.example.store.dao;

public interface DaoFactory {

    UserDao getUserDao();

    CategoryDao getCategoryDao();

    ItemDao getItemDao();
}
