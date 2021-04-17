package com.example.store.dao.impl.inmemory;

import com.example.store.dao.*;

class InMemoryDaoFactory implements DaoFactory {

    InMemoryDatabase database;

    MovieDao movieDao;
    CommentDao commentDao;
    UserDao userDao;
    ItemDao itemDao;
    CategoryDao categoryDao;

    InMemoryDaoFactory(InMemoryDatabase database) {
        this.database = database;

        movieDao = new InMemoryMovieDao(database);
        commentDao = new InMemoryCommentDao(database);
        userDao = new InMemoryUserDao(database);
        categoryDao = new InMemoryCategoryDao(database);
        itemDao = new InMemoryItemDao(database);
    }

    @Override
    public CommentDao getCommentDao() {
        return commentDao;
    }

    @Override
    public MovieDao getMovieDao() {
        return movieDao;
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
