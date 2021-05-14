package com.example.store.dao;

import com.example.store.model.Category;
import com.example.store.model.Item;

import java.util.Collection;

public interface ItemDao extends AbstractDao<Item> {

    Collection<Item> findByCategoryId(Integer category);

    void addItem(Category category, String maker, String model, String description);

    void deleteItem(Collection<Category> category, Item item);
}
