package com.natsa.natsa20_mobile.model.social_media;

import com.natsa.natsa20_mobile.model.Status;

import java.util.List;

public class GetSocialMediaResponse {
    private Status status;
    private List<SocialMedia> data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<SocialMedia> getData() {
        return data;
    }

    public void setData(List<SocialMedia> data) {
        this.data = data;
    }
}
