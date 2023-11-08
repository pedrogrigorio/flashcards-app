package com.example.flashcards_app.models;

public class Notification {
    String notificationText;
    String imgSrc;

    public Notification(String notificationText, String imgSrc) {
        this.notificationText = notificationText;
        this.imgSrc = imgSrc;
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
