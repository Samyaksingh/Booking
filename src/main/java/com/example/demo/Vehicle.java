package com.example.demo;

public class Vehicle {

    private String rc_no;
    private String description;
    private int tariff_day;
    private int tariff_km;
    private int free_km;

    public Vehicle(String rc_no, String description, int tariff_day, int tariff_km, int free_km) {
        this.rc_no = rc_no;
        this.description = description;
        this.tariff_day = tariff_day;
        this.tariff_km = tariff_km;
        this.free_km = free_km;
    }

    public String getRc_no() {
        return rc_no;
    }

    public void setRc_no(String rc_no) {
        this.rc_no = rc_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTariff_day() {
        return tariff_day;
    }

    public void setTariff_day(int tariff_day) {
        this.tariff_day = tariff_day;
    }

    public int getTariff_km() {
        return tariff_km;
    }

    public void setTariff_km(int tariff_km) {
        this.tariff_km = tariff_km;
    }

    public int getFree_km() {
        return free_km;
    }

    public void setFree_km(int free_km) {
        this.free_km = free_km;
    }
}
