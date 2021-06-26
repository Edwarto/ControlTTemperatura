package com.example.monitoreoestadoambiental.Model;

public class RegistroHumedad {
    private String datetime, hum;

    public RegistroHumedad(String datetime, String hum) {
        this.datetime = datetime;
        this.hum = hum;
    }
    public RegistroHumedad() {
        this.datetime = datetime;
        this.hum = hum;
    }

    public String getDatetime() {

        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }
}
