package com.example.store.dao;

import com.example.store.model.Admin;

public interface UserDao extends AbstractDao<Admin> {

    Admin getByLogin(String login);
}
