package com.natsa.natsa20_mobile.model.social_media.add_new_social_media;

public class Request {
    private Integer sosmedId;
    private String sosmedLink;

    public Request(Integer sosmedId, String sosmedLink) {
        this.sosmedId = sosmedId;
        this.sosmedLink = sosmedLink;
    }

    public Integer getSosmedId() {
        return sosmedId;
    }

    public void setSosmedId(Integer sosmedId) {
        this.sosmedId = sosmedId;
    }

    public String getSosmedLink() {
        return sosmedLink;
    }

    public void setSosmedLink(String sosmedLink) {
        this.sosmedLink = sosmedLink;
    }
}
