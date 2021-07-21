package com.natsa.natsa20_mobile.model.vestiges;

import com.natsa.natsa20_mobile.model.Status;

import java.util.List;

public class Vestige {
    private Status status;
    private Data data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
