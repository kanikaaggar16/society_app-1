package com.example.society_app;

public class viewexp {

    private String givento;

    private String on;

    private String ruppes;


    public viewexp() {
    }

    public viewexp(String givento, String on, String ruppes) {
        this.givento = givento;
        this.on = on;
        this.ruppes = ruppes;
    }

    public String getGivento() {
        return givento;
    }

    public void setGivento(String givento) {
        this.givento = givento;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getRuppes() {
        return ruppes;
    }

    public void setRuppes(String ruppes) {
        this.ruppes = ruppes;
    }
}
