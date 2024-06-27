package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String x;
    private String o;
    private String h;
    private String l;
    private String c;

    public StockData(String x, String o, String h, String l, String c) {
        this.x = x;
        this.o = o;
        this.h = h;
        this.l = l;
        this.c = c;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public StockData() {

    }
}
