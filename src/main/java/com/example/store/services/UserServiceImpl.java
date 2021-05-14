package com.example.store.services;

import com.example.store.dao.DaoFactory;
import com.example.store.model.Admin;

import java.util.function.UnaryOperator;

public class UserServiceImpl implements UserService {

    DaoFactory daoFactory;
    UnaryOperator<String> passwordHasher;

    public UserServiceImpl(DaoFactory daoFactory, UnaryOperator<String> passwordHasher) {
        this.daoFactory = daoFactory;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Admin getByLogin(String login) {
        return daoFactory.getUserDao().getByLogin(login);
    }

    @Override
    public boolean checkPassword(Admin user, String password) {
        return user.getPasswordHash().equals(passwordHasher.apply(password));
    }

}
