package com.example.store.dao.impl.inmemory;

import com.example.store.model.Category;
import com.example.store.model.Admin;

import java.util.Arrays;
import java.util.List;

public class InMemoryTestData {

    public static void generateTo(InMemoryDatabase database) {
        database.users.clear();
        database.items.clear();
        database.categories.clear();

        Admin alice = new Admin(1, "Alice", "alice@example.com", "passwordhash");
        Admin bob = new Admin(2, "Bob", "bob@example.com", "passwordhash");
        Admin charlie = new Admin(3, "Charlie", "charlie@example.com", "passwordhash");
        Admin diana = new Admin(4, "Diana", "diana@example.com", "passwordhash");
        Admin evil = new Admin(5, "Evil Emperror", "evil@example.com", "passwordhash");
        List<Admin> users = Arrays.asList(alice, bob, charlie, diana, evil);
        users.forEach(user -> database.users.put(user.getUserId(), user));

        Category householdAppliances = new Category(1, null, "Household appliances");
        Category smartPhones = new Category(2, null, "Smartphones");
        List<Category> categories = Arrays.asList(householdAppliances, smartPhones);
        categories.forEach(category -> database.categories.put(category.getCategoryId(), category));
    }
}
