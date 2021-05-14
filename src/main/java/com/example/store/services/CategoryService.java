package com.example.store.services;

import com.example.store.model.Category;
import com.example.store.model.Item;

import java.util.Collection;
import java.util.List;

public interface CategoryService {

    Collection<Category> getAllCategories();

    Collection<Category> searchRootCategories(String text);

    List<Category> getCategoriesContainingItem(Integer itemId);

    Category getCategoryById(Integer categoryId);

    Item getItemById(Integer itemId);

    void addItem(Category category, String maker, String model, String description);

    void editItem(Item item, String maker, String model, String description);

    void addCategory(Category parent, String title);

    void editCategory(Category category, String text);

    void deleteCategory(Category category);

    void deleteItem(Collection<Category> category, Item item);
}
