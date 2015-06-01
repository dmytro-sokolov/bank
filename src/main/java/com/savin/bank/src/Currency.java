package com.savin.bank.src;


import com.savin.bank.dao.Identified;

import java.io.Serializable;

public class Currency implements Serializable, Identified<Integer> {
    private long serialVersionUID = 1L;
    private int id;
    private double valute;
    private String type;

    public Currency() {}

    public Integer getId() {
        return id;
    }

    public double getValute() {
        return valute;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValute(double valute) {
        this.valute = valute;
    }

    public void setType(String type) {
        this.type = type;
    }
}
