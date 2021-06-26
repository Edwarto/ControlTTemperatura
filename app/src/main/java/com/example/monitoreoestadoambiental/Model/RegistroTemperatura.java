package com.example.monitoreoestadoambiental.Model;

public class RegistroTemperatura {
    private String datetime, temp;

    public RegistroTemperatura(String datetime, String temp) {
        this.datetime = datetime;
        this.temp = temp;
    }
    public RegistroTemperatura() {
        this.datetime = datetime;
        this.temp = temp;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
