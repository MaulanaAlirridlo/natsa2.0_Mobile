package com.natsa.natsa20_mobile.model.products;

import com.natsa.natsa20_mobile.model.Status;

public class DeletePhotoResponse {
    private Status status;
    private String riceField;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRiceField() {
        return riceField;
    }

    public void setRiceField(String riceField) {
        this.riceField = riceField;
    }
}
