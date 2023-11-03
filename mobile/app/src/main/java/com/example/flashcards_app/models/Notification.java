package com.example.flashcards_app.models;

public class Notification {
    String notificationText;
    String imgSrc;

    public Notification(String notificationText) {
        this.notificationText = notificationText;
        this.imgSrc = "https://cdn.vectorstock.com/i/preview-1x/08/19/gray-photo-placeholder-icon-design-ui-vector-35850819.jpg";
    }
    public String getNotificationText() {
        return notificationText;
    }
    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
    public String getImgSrc() {
        return imgSrc;
    }
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
