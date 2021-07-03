package com.natsa.natsa20_mobile.model.products.user_add_product;

import java.util.List;

import okhttp3.MultipartBody;

public class Request {
    private String title;
    private int harga;
    private int luas;
    private String alamat;
    private String deskripsi;
    private String maps;
    private String sertifikasi;
    private String tipe;
    private String pemilik;
    private String vestige;
    private String region;
    private String irrigation;
    private List<MultipartBody.Part> photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getLuas() {
        return luas;
    }

    public void setLuas(int luas) {
        this.luas = luas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public String getSertifikasi() {
        return sertifikasi;
    }

    public void setSertifikasi(String sertifikasi) {
        this.sertifikasi = sertifikasi;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getVestige() {
        return vestige;
    }

    public void setVestige(String vestige) {
        this.vestige = vestige;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(String irrigation) {
        this.irrigation = irrigation;
    }

    public List<MultipartBody.Part> getPhoto() {
        return photo;
    }

    public void setPhoto(List<MultipartBody.Part> photo) {
        this.photo = photo;
    }
}
