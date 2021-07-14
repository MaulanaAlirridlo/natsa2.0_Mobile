package com.natsa.natsa20_mobile.model.bookmark.delete_bookmark;

import com.natsa.natsa20_mobile.model.Status;

public class DeleteBookmarkRespone {
    private Status status;
    private int riceField;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRiceField() {
        return riceField;
    }

    public void setRiceField(int riceField) {
        this.riceField = riceField;
    }
}
