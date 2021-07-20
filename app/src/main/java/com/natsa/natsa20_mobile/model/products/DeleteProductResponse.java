package com.natsa.natsa20_mobile.model.products;

import com.natsa.natsa20_mobile.model.Status;

public class DeleteProductResponse {
    Status status;
    Integer riceField;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getRiceField() {
        return riceField;
    }

    public void setRiceField(Integer riceField) {
        this.riceField = riceField;
    }
}
