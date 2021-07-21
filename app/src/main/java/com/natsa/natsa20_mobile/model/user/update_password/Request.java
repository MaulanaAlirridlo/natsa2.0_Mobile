package com.natsa.natsa20_mobile.model.user.update_password;

public class Request {
    private String current_password;
    private String password;
    private String password_confirmation;

    public Request(String current_password, String password, String password_confirmation) {
        this.current_password = current_password;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
