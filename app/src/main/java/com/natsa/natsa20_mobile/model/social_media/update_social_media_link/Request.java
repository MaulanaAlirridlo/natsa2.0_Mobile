package com.natsa.natsa20_mobile.model.social_media.update_social_media_link;

public class Request {
    private String updateLink;

    public Request(String updateLink) {
        this.updateLink = updateLink;
    }

    public String getUpdateLink() {
        return updateLink;
    }

    public void setUpdateLink(String updateLink) {
        this.updateLink = updateLink;
    }
}
