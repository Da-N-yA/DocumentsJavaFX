package ru.documents.documents.lists;

import java.time.LocalDate;

public class Nakladnaya extends Documents {
    private String currency;
    private double exchangeRate;
    private String product;
    private double quantity;

    public Nakladnaya(String number, LocalDate date, String user, double amount, String currency, double exchangeRate, String product, double quantity) {
        super(number, date, user, amount);
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.product = product;
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Накладная от " + date + " номер " + number;
    }
}