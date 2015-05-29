package com.savin.bank.java;

import com.savin.bank.dao.Identified;

import java.beans.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by denis on 5/25/15.
 */
public class Client implements Serializable, Identified<Integer>{

    private int id;
    private String name;
    private String surname;
    private String secondName;
    private Date dateOfBirth;
    private Date dateOfRegistration;

    public Client() {}

    public Client(int clientId) {
        id = clientId;

    }  //todo: Ask for ask for the correctness of the constructor

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @SuppressWarnings("deprecation")
    public void setDateOfBirth(Date args) {
//        int [] dateArray = Arrays.stream(args.split("-")).mapToInt(
//                Integer::parseInt).toArray();
        dateOfBirth = args;
    }
    @SuppressWarnings("deprecation")
    public void setDateOfRegistration(Date args) {
        dateOfRegistration = args;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
