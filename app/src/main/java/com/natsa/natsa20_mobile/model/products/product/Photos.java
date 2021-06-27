package com.natsa.natsa20_mobile.model.products.product;

public class Photos {
    private int id;
    private int rice_field_id;
    private String photo_path;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRice_field_id() {
        return rice_field_id;
    }

    public void setRice_field_id(int rice_field_id) {
        this.rice_field_id = rice_field_id;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
