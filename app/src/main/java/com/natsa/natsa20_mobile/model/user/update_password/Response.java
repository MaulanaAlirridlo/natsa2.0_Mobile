package com.natsa.natsa20_mobile.model.user.update_password;

import com.natsa.natsa20_mobile.model.Status;

public class Response {
    private Status status;
    private Boolean user;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }
}
