package com.example.footbalhighlight;

public class FootballModel {

    String title;
    String catagory;
    String date;
    String thumbnail;

    public String getVedioHtml() {
        return vedioHtml;
    }

    public void setVedioHtml(String vedioHtml) {
        this.vedioHtml = vedioHtml;
    }

    String vedioHtml;


    public FootballModel(String title, String catagory ,String date,String thumbnail,String vedioHtml ) {
        this.title= title;
        this.catagory= catagory;
        this.date= date;
        this.thumbnail= thumbnail;
        this.vedioHtml= vedioHtml;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
