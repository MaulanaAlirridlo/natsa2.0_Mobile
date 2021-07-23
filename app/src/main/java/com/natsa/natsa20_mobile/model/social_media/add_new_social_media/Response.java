package com.natsa.natsa20_mobile.model.social_media.add_new_social_media;

import com.natsa.natsa20_mobile.model.Status;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;

public class Response {
    private Status status;
    private UserSocialMedia userSocialMedia;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserSocialMedia getUserSocialMedia() {
        return userSocialMedia;
    }

    public void setUserSocialMedia(UserSocialMedia userSocialMedia) {
        this.userSocialMedia = userSocialMedia;
    }
}
