package com.savin.bank.src;


import com.savin.bank.dao.Identified;

import java.io.Serializable;

public class Account implements Identified<Integer>, Serializable {
    private long serialVersionUID = 1L;
    private int user_id;
    private int id;
    private double balance;
    private Currency currency;
    private String type;

    public Account() {}


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setType(String type) {
        this.type = type;
    }
}
