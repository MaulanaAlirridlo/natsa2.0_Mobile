package com.natsa.natsa20_mobile.model;

import android.net.Uri;

public class AttachmentListData {
    private String imageName;

    private String imageUri;

    private String imagePath;

    private Uri realUri;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Uri getRealUri() {
        return realUri;
    }

    public void setRealUri(Uri realUri) {
        this.realUri = realUri;
    }
}
