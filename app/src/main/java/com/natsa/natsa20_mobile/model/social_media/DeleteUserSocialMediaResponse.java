package com.natsa.natsa20_mobile.model.social_media;

import com.natsa.natsa20_mobile.model.Status;

public class DeleteUserSocialMediaResponse {
    private Status status;
    private Integer UserSocialMedia;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getUserSocialMedia() {
        return UserSocialMedia;
    }

    public void setUserSocialMedia(Integer userSocialMedia) {
        UserSocialMedia = userSocialMedia;
    }
}
