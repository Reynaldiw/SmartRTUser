package com.reynaldiwijaya.smartrt.ui.Intro;

public class ScreenItem {

    private String Title, Description;
    private int screenImg;

    public ScreenItem(String title, String description, int screenImg) {
        Title = title;
        Description = description;
        this.screenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(int screenImg) {
        this.screenImg = screenImg;
    }
}
