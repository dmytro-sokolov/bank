package com.savin.bank.dao;


import java.io.Serializable;

public interface Identified <PK extends Serializable>{
    public PK getId();
}
