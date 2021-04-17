package com.example.store.model;

import java.util.List;

public class Category {
    private Integer categoryId;
    private Integer parentCategoryId;
    private List<Category> subCategories;
    private String title;
    private List<Item> items;

    public Category(Integer categoryId, Integer parentCategoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getNumberOfItems() {
        int result = 0;

        if (subCategories != null) {
            for (Category subCategory : subCategories) {
                result += subCategory.getNumberOfItems();
            }
        }

        if (items != null) {
            result += items.size();
        }

        return result;
    }
}
