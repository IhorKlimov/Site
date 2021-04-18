package com.example.store.dao.impl.inmemory;

import com.example.store.dao.ItemDao;
import com.example.store.model.Category;
import com.example.store.model.Item;

import java.util.ArrayList;
import java.util.Collection;

class InMemoryItemDao extends InMemoryAbstractDao<Item> implements ItemDao {

    InMemoryItemDao(InMemoryDatabase database) {
        super(database.items, Item::getItemId, Item::setItemId, database);
    }

    @Override
    public Collection<Item> findByCategoryId(Integer categoryId) {
        return database.categories.get(categoryId).getItems();
    }

    @Override
    public void addItem(Category category, String maker, String model, String description) {
        Item item = new Item(-1, maker, model, description);

        this.insert(item, true);
        if (category.getItems() == null) {
            category.setItems(new ArrayList<>());
        }

        category.getItems().add(item);
    }

    @Override
    public void deleteItem(Collection<Category> category, Item item) {
        System.out.println("item is null" + (item ==null) + " "+ category.size());
        this.delete(item);
        for (Category c : category) {
            c.getItems().removeIf(i -> i.getItemId().equals(item.getItemId()));
        }
    }
}
