package com.example.store.dao.impl.inmemory;

import com.example.store.dao.UserDao;
import com.example.store.model.Admin;

class InMemoryUserDao extends InMemoryAbstractDao<Admin> implements UserDao {

    InMemoryUserDao(InMemoryDatabase database) {
        super(database.users, Admin::getUserId, Admin::setUserId, database);
    }

    @Override
    public Admin getByLogin(String login) {
        return database.users.values()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

}
