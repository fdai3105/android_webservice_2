package com.example.doan_android_2020._general.models;

import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;

public class Item{
    private int idItem;
    private String nameItem;
    private String categoryItem;
    private String imageItem;
    private int priceItem;
    private ItemPromotion itemPromotion;
    private String bioItem;
    private double reviewItem;

    public Item(int idItem, String nameItem, String categoryItem, String imageItem, int priceItem, ItemPromotion itemPromotion, String bioItem, double reviewItem) {
        this.idItem = idItem;
        this.nameItem = nameItem;
        this.categoryItem = categoryItem;
        this.imageItem = imageItem;
        this.priceItem = priceItem;
        this.itemPromotion = itemPromotion;
        this.bioItem = bioItem;
        this.reviewItem = reviewItem;
    }

    public ItemPromotion getItemPromotion() {
        return itemPromotion;
    }

    public void setItemPromotion(ItemPromotion itemPromotion) {
        this.itemPromotion = itemPromotion;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getCategoryItem() {
        return categoryItem;
    }

    public void setCategoryItem(String categoryItem) {
        this.categoryItem = categoryItem;
    }

    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

    public int getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(int priceItem) {
        this.priceItem = priceItem;
    }

    public String getBioItem() {
        return bioItem;
    }

    public void setBioItem(String bioItem) {
        this.bioItem = bioItem;
    }

    public double getReviewItem() {
        return reviewItem;
    }

    public void setReviewItem(double reviewItem) {
        this.reviewItem = reviewItem;
    }
}
