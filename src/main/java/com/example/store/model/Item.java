package com.example.store.model;

public class Item {
    private Integer itemId;
    private String maker;
    private String model;
    private String description;

    public Item(Integer itemId, String maker, String model, String description) {
        this.itemId = itemId;
        this.maker = maker;
        this.model = model;
        this.description = description;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
