package com.natsa.natsa20_mobile.model.user;

import com.natsa.natsa20_mobile.model.Status;

import java.util.List;

public class GetUserRes {
    Status status;
    List<User> users;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
