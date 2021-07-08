package com.natsa.natsa20_mobile.model.products.products;

public class Data {
    private int id;
    private String title;
    private int harga;
    private String regions;
    private Photo photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        return false;
    }

}
