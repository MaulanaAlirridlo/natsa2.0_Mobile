package com.natsa.natsa20_mobile.model.products.product;

import com.natsa.natsa20_mobile.model.Status;

import java.util.List;

public class Product {

    private Status status;
    private RiceField riceField;
    private List<RandomRiceFields> randomRiceFields;
    private List<Integer> tes;

    public List<Integer> getTes() {
        return tes;
    }

    public void setTes(List<Integer> tes) {
        this.tes = tes;
    }

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

    public List<RandomRiceFields> getRandomRiceFields() {
        return randomRiceFields;
    }

    public void setRandomRiceFields(List<RandomRiceFields> randomRiceFields) {
        this.randomRiceFields = randomRiceFields;
    }
}
