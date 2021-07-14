package com.natsa.natsa20_mobile.model.regions;

import com.natsa.natsa20_mobile.model.Status;

import java.util.List;

public class Regions {
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
