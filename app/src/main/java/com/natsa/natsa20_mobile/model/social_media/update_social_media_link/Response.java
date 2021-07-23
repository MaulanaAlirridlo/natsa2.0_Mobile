package com.natsa.natsa20_mobile.model.social_media.update_social_media_link;

import com.natsa.natsa20_mobile.model.Status;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;

public class Response {
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
