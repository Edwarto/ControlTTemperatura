package com.example.monitoreoestadoambiental.Model;

public class RegistroSonido {
    private String datetime, son;

    public RegistroSonido(String datetime, String son) {
        this.datetime = datetime;
        this.son = son;
    }

    public RegistroSonido() {
        this.datetime = datetime;
        this.son = son;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }
}
