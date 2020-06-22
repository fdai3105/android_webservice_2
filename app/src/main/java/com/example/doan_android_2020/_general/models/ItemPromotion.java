package com.example.doan_android_2020._general.models;

public class ItemPromotion {
    private boolean itemPromotionFreeship;
    private boolean itemPromotionBigSale;
    private boolean itemPromotionBestSeller;

    public ItemPromotion(boolean itemPromotionFreeship, boolean itemPromotionBigSale, boolean itemPromotionBestSeller) {
        this.itemPromotionFreeship = itemPromotionFreeship;
        this.itemPromotionBigSale = itemPromotionBigSale;
        this.itemPromotionBestSeller = itemPromotionBestSeller;
    }

    public boolean isItemPromotionFreeship() {
        return itemPromotionFreeship;
    }

    public void setItemPromotionFreeship(boolean itemPromotionFreeship) {
        this.itemPromotionFreeship = itemPromotionFreeship;
    }

    public boolean isItemPromotionBigSale() {
        return itemPromotionBigSale;
    }

    public void setItemPromotionBigSale(boolean itemPromotionBigSale) {
        this.itemPromotionBigSale = itemPromotionBigSale;
    }

    public boolean isItemPromotionBestSeller() {
        return itemPromotionBestSeller;
    }

    public void setItemPromotionBestSeller(boolean itemPromotionBestSeller) {
        this.itemPromotionBestSeller = itemPromotionBestSeller;
    }

    @Override
    public String toString() {
        return "ItemPromotion{" +
                "itemPromotionFreeship=" + itemPromotionFreeship +
                ", itemPromotionBigSale=" + itemPromotionBigSale +
                ", itemPromotionBestSeller=" + itemPromotionBestSeller +
                '}';
    }
}
