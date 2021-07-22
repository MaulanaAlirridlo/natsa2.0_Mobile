package com.natsa.natsa20_mobile.model.social_media;

import com.natsa.natsa20_mobile.model.Status;

import java.util.List;

public class GetUserSocialMediaResponse {
    private Status status;
    private List<UserSocialMedia> userSocialMedia;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<UserSocialMedia> getUserSocialMedia() {
        return userSocialMedia;
    }

    public void setUserSocialMedia(List<UserSocialMedia> userSocialMedia) {
        this.userSocialMedia = userSocialMedia;
    }
}
