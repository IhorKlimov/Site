package com.example.store.services;

import com.example.store.model.Admin;

public interface UserService {

    Admin getByLogin(String login);

    boolean checkPassword(Admin user, String password);
}
