package com.maulana.natsa20_mobile.model.product;

import java.util.List;

public class Product {

    private Status status;
    private List<RiceField> riceField;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<RiceField> getRiceField() {
        return riceField;
    }

    public void setRiceField(List<RiceField> riceField) {
        this.riceField = riceField;
    }
}
