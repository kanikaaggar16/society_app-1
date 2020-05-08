package com.example.society_app;

public class viewmtn {

    private  String amount;
    private  String date;
    private  String house_no;

    public viewmtn() {
    }

    public viewmtn(String amount, String date, String house_no) {
        this.amount = amount;
        this.date = date;
        this.house_no = house_no;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }
}



