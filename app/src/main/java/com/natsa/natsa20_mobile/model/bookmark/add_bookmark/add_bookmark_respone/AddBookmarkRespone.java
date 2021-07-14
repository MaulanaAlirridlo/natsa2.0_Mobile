package com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone;

import com.natsa.natsa20_mobile.model.Status;

public class AddBookmarkRespone {
    private Status status;
    private RiceField riceField;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RiceField getRiceField() {
        return riceField;
    }

    public void setRiceField(RiceField riceField) {
        this.riceField = riceField;
    }
}
