package com.example.store.services;

import com.example.store.model.User;

public interface UserService {

    User getByLogin(String login);

    boolean checkPassword(User user, String password);
}
