package com.example.store.dao.impl.inmemory;

import com.example.store.dao.CategoryDao;
import com.example.store.dao.DaoFactory;
import com.example.store.dao.ItemDao;
import com.example.store.dao.UserDao;

class InMemoryDaoFactory implements DaoFactory {

    InMemoryDatabase database;

    UserDao userDao;
    ItemDao itemDao;
    CategoryDao categoryDao;

    InMemoryDaoFactory(InMemoryDatabase database) {
        this.database = database;

        userDao = new InMemoryUserDao(database);
        categoryDao = new InMemoryCategoryDao(database);
        itemDao = new InMemoryItemDao(database);
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}
