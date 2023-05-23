package com.example.youtubeshorts;

public class thumbnailData {
    String image,mediaUrl;

    public thumbnailData() {
    }
    public thumbnailData(String image,String mediaUrl) {
        this.image=image;
        this.mediaUrl=mediaUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
