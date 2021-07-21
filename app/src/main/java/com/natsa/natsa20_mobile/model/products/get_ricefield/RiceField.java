package com.natsa.natsa20_mobile.model.products.get_ricefield;

import com.natsa.natsa20_mobile.model.irrigations.Irrigations;
import com.natsa.natsa20_mobile.model.products.product.Photo;
import com.natsa.natsa20_mobile.model.regions.Regions;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.model.vestiges.Data;
import com.natsa.natsa20_mobile.model.vestiges.Vestiges;

import java.util.List;

public class RiceField {
    private Integer id;
    private String title;
    private Integer harga;
    private Integer luas;
    private String alamat;
    private String maps;
    private Object vector;
    private String deskripsi;
    private String sertifikasi;
    private String tipe;
    private String createdAt;
    private String updatedAt;
    private Integer user_id;
    private Integer vestige_id;
    private Integer irrigation_id;
    private Integer region_id;
    private String ketersediaan;
    private User user;
    private com.natsa.natsa20_mobile.model.vestiges.Data vestige;
    private com.natsa.natsa20_mobile.model.irrigations.Data irrigation;
    private com.natsa.natsa20_mobile.model.regions.Data region;
    private List<Photo> photos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getLuas() {
        return luas;
    }

    public void setLuas(Integer luas) {
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

    public Object getVector() {
        return vector;
    }

    public void setVector(Object vector) {
        this.vector = vector;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVestige_id() {
        return vestige_id;
    }

    public void setVestige_id(Integer vestige_id) {
        this.vestige_id = vestige_id;
    }

    public Integer getIrrigation_id() {
        return irrigation_id;
    }

    public void setIrrigation_id(Integer irrigation_id) {
        this.irrigation_id = irrigation_id;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public String getKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(String ketersediaan) {
        this.ketersediaan = ketersediaan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Data getVestige() {
        return vestige;
    }

    public void setVestige(Data vestige) {
        this.vestige = vestige;
    }

    public com.natsa.natsa20_mobile.model.irrigations.Data getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(com.natsa.natsa20_mobile.model.irrigations.Data irrigation) {
        this.irrigation = irrigation;
    }

    public com.natsa.natsa20_mobile.model.regions.Data getRegion() {
        return region;
    }

    public void setRegion(com.natsa.natsa20_mobile.model.regions.Data region) {
        this.region = region;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
