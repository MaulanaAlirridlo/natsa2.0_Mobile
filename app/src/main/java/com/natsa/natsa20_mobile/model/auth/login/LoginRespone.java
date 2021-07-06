package com.natsa.natsa20_mobile.model.auth.login;

import com.natsa.natsa20_mobile.model.User;

public class LoginRespone {
    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
