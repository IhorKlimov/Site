package com.example.store.dao;

public interface DaoFactory {

    CommentDao getCommentDao();

    MovieDao getMovieDao();

    UserDao getUserDao();
}
