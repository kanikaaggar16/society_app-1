package com.example.society_app;

public class viewmtg {

    private  String topic;
    private  String date;
    private  String time;

    public viewmtg() {
    }

    public viewmtg(String topic, String date, String time) {
        this.topic = topic;
        this.date = date;
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
