package com.natsa.natsa20_mobile.model.social_media;

public class UserSocialMedia {
    Integer id;
    Integer user_id;
    Integer social_media_id;
    String link;
    String created_at;
    String updated_at;
    SocialMedia social_media;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSocial_media_id() {
        return social_media_id;
    }

    public void setSocial_media_id(Integer social_media_id) {
        this.social_media_id = social_media_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public SocialMedia getSocial_media() {
        return social_media;
    }

    public void setSocial_media(SocialMedia social_media) {
        this.social_media = social_media;
    }
}
