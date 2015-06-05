package com.savin.bank.src;

import com.savin.bank.dao.Identified;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Client implements Serializable, Identified<Integer>{
    private long serialVersionUID = 1L;
    private int id;
    private String name;
    private String surname;
    private String secondName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Date dateOfRegistration;
    private ConcurrentHashMap<String,Account> accountsList;

    public Client() {}

    public Client(int clientId) {
        id = clientId;

    }  //todo: Ask for ask for the correctness of the constructor

    public void setId(int id) {
        this.id = id;
    }

    public ConcurrentHashMap<String, Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(ConcurrentHashMap<String, Account> accountsList) {
        this.accountsList = accountsList;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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