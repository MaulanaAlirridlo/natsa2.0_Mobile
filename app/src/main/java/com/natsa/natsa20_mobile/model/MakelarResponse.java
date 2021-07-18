package com.natsa.natsa20_mobile.model;

import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;
import com.natsa.natsa20_mobile.model.user.User;

import java.util.List;

public class MakelarResponse {
    Status status;
    List<Data> riceField;
    User user;
    List<UserSocialMedia> makelarSocialMedias;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Data> getRiceField() {
        return riceField;
    }

    public void setRiceField(List<Data> riceField) {
        this.riceField = riceField;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserSocialMedia> getMakelarSocialMedias() {
        return makelarSocialMedias;
    }

    public void setMakelarSocialMedias(List<UserSocialMedia> makelarSocialMedias) {
        this.makelarSocialMedias = makelarSocialMedias;
    }
}
