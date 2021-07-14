package com.natsa.natsa20_mobile.model.products.products;

import com.natsa.natsa20_mobile.model.Status;

public class Products {

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
