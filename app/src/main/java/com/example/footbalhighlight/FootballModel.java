package com.example.footbalhighlight;

public class FootballModel {

    String title, catagory , date;

    public FootballModel(String title, String catagory ,String date ) {
        this.title= title;
        this.catagory= catagory;
        this.date= date;
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
}
