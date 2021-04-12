package com.example.store.model;

import java.util.List;

public class Category {
    private List<Category> subCategories;
    private String title;

    public Category(List<Category> subCategories, String title) {
        this.subCategories = subCategories;
        this.title = title;
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
}
