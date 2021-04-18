package com.example.store.dao.impl.inmemory;

import com.example.store.model.Category;
import com.example.store.model.User;

import java.util.Arrays;
import java.util.List;

public class InMemoryTestData {

    public static void generateTo(InMemoryDatabase database) {
        database.users.clear();
        database.items.clear();
        database.categories.clear();

        User alice = new User(1, "Alice", "alice@example.com", "passwordhash");
        User bob = new User(2, "Bob", "bob@example.com", "passwordhash");
        User charlie = new User(3, "Charlie", "charlie@example.com", "passwordhash");
        User diana = new User(4, "Diana", "diana@example.com", "passwordhash");
        User evil = new User(5, "Evil Emperror", "evil@example.com", "passwordhash");
        List<User> users = Arrays.asList(alice, bob, charlie, diana, evil);
        users.forEach(user -> database.users.put(user.getUserId(), user));

        Category householdAppliances = new Category(1, null, "Household appliances");
        Category smartPhones = new Category(2, null, "Smartphones");
        List<Category> categories = Arrays.asList(householdAppliances, smartPhones);
        categories.forEach(category -> database.categories.put(category.getCategoryId(), category));
    }
}
