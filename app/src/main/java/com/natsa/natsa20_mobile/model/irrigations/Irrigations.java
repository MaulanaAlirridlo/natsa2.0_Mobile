package com.natsa.natsa20_mobile.model.irrigations;

import java.util.List;

public class Irrigations {
    private Status status;
    private List<Data> data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
