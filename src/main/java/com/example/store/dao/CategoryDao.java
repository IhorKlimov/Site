package com.example.store.dao;

import com.example.store.model.Category;

public interface CategoryDao extends AbstractDao<Category> {

    void addSubcategory(Category parent, String title);

    void deleteCategory(Category category);
}
