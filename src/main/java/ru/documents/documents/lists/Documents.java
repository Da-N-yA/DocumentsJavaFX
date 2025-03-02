package ru.documents.documents.lists;

import java.time.LocalDate;

public abstract class Documents {
    protected String number;
    protected LocalDate date;
    protected String user;
    protected double amount;

    public Documents(String number, LocalDate date, String user, double amount) {
        this.number = number;
        this.date = date;
        this.user = user;
        this.amount = amount;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public abstract String toString();
}
