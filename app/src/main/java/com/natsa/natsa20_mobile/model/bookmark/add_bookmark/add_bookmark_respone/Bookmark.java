package com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone;

public class Bookmark {
    private int id;
    private int rice_field_id;
    private int user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
