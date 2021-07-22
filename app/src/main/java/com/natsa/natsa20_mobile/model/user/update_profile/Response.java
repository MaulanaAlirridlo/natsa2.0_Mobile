package com.natsa.natsa20_mobile.model.user.update_profile;

import com.natsa.natsa20_mobile.model.Status;
import com.natsa.natsa20_mobile.model.user.User;

public class Response {
    private Status status;
    private User user;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
