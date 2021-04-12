package com.example.store.dao;

import com.example.store.model.User;

public interface UserDao extends AbstractDao<User> {

    User getByLogin(String login);
}
