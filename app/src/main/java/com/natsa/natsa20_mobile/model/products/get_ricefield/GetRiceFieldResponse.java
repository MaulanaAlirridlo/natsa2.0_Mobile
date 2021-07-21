package com.natsa.natsa20_mobile.model.products.get_ricefield;

import com.natsa.natsa20_mobile.model.Status;

public class GetRiceFieldResponse {
    Status status;
    RiceField riceField;

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
