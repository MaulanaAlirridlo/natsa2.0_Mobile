package com.natsa.natsa20_mobile.model.user.update_profile;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

public class Request {
    String name;
    String email;
    String username;
    String ktp;
    String no_hp;
    MultipartBody.Part photo;

    public Request(String name, String email, String username, String ktp, String no_hp, MultipartBody.Part photo) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.ktp = ktp;
        this.no_hp = no_hp;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public MultipartBody.Part getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartBody.Part photo) {
        this.photo = photo;
    }
}
