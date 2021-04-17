package com.example.store.services;

import com.example.store.dao.DaoFactory;
import com.example.store.model.Category;
import com.example.store.model.Item;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    DaoFactory daoFactory;

    public CategoryServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return daoFactory.getCategoryDao().get(categoryId);
    }

    @Override
    public Item getItemById(Integer itemId) {
        return daoFactory.getItemDao().get(itemId);
    }

    @Override
    public void addItem(Category Category, String maker, String model, String description) {
        daoFactory.getItemDao().addItem(Category, maker, model, description);
    }

    @Override
    public void editItem(Item item, String maker, String model, String description) {
        item.setMaker(maker);
        item.setModel(model);
        item.setDescription(description);
    }

    @Override
    public void addCategory(Category parent, String title) {
        daoFactory.getCategoryDao().addSubcategory(parent, title);
    }

    @Override
    public void editCategory(Category category, String text) {
        category.setTitle(text);
    }

    @Override
    public void deleteCategory(Category category) {
        daoFactory.getCategoryDao().deleteCategory(category);
    }

    @Override
    public void deleteItem(Collection<Category> category, Item item) {
        daoFactory.getItemDao().deleteItem(category, item);
    }

    @Override
    public Collection<Category> getAllCategories() {
        return daoFactory.getCategoryDao().findAll();
    }

    @Override
    public Collection<Category> searchRootCategories(String text) {
//        if (text == null || text.equals("")) {
        Collection<Category> allCategories = getAllCategories();
        allCategories.removeIf(next -> next.getParentCategoryId() != null);
        return allCategories;
//        }
//        return daoFactory.getCategoryDao().findByText(text);
    }

    @Override
    public List<Category> getCategoriesContainingItem(Integer itemId) {
        System.out.println("getCategoriesContainingItem "+ itemId);
        return daoFactory.getCategoryDao().findAll()
                .stream()
                .filter(c -> c.getItems() != null && c.getItems().stream().anyMatch(i -> i.getItemId().equals(itemId)))
                .collect(Collectors.toList());
    }


}
