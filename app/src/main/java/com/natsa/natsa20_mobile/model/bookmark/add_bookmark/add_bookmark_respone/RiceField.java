package com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone;

import java.util.List;

public class RiceField {
    private int id;
    private String title;
    private int harga;
    private int luas;
    private String alamat;
    private String maps;
    private String deskripsi;
    private String sertifikasi;
    private String tipe;
    private String created_at;
    private String updated_at;
    private int user_id;
    private int vestige_id;
    private int irrigation_id;
    private int region_id;
    private int verification_id;
    private List<Bookmark> Bookmark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVestige_id() {
        return vestige_id;
    }

    public void setVestige_id(int vestige_id) {
        this.vestige_id = vestige_id;
    }

    public int getIrrigation_id() {
        return irrigation_id;
    }

    public void setIrrigation_id(int irrigation_id) {
        this.irrigation_id = irrigation_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public int getVerification_id() {
        return verification_id;
    }

    public void setVerification_id(int verification_id) {
        this.verification_id = verification_id;
    }

    public List<com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone.Bookmark> getBookmark() {
        return Bookmark;
    }

    public void setBookmark(List<com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone.Bookmark> bookmark) {
        Bookmark = bookmark;
    }
}
