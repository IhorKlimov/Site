package com.example.store.dao.impl.inmemory;

import com.example.store.dao.CategoryDao;
import com.example.store.model.Category;
import com.example.store.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

class InMemoryCategoryDao extends InMemoryAbstractDao<Category> implements CategoryDao {

    InMemoryCategoryDao(InMemoryDatabase database) {
        super(database.categories, Category::getCategoryId, Category::setCategoryId, database);
    }

    @Override
    public void addSubcategory(Category parent, String title) {
        Category category = new Category(-1, parent != null ? parent.getCategoryId() : null,  title);

        this.insert(category, true);
        if (parent != null) {
            if (parent.getSubCategories() == null) {
                parent.setSubCategories(new ArrayList<>());
            }

            parent.getSubCategories().add(category);
        }
    }

    @Override
    public void deleteCategory(Category category) {
        this.delete(category);
        if (category.getParentCategoryId() != null) {
            Category parent = this.get(category.getParentCategoryId());
            parent.getSubCategories().removeIf(c -> c.getCategoryId().equals(category.getCategoryId()));
        }
    }
}
