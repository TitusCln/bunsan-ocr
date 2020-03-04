package com.company;

public class Account {

    private String number;
    private String status;

    public Account(String number, String status) {
        this.number = number;
        this.status = status;
    }

    @Override
    public String toString() {
        return this.number+" "+this.status;
    }
}
