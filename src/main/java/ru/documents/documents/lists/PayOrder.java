package ru.documents.documents.lists;
import java.time.LocalDate;

public class PayOrder extends Documents {
    private String counterparty;
    private String currency;
    private double exchangeRate;
    private double commission;

    public PayOrder(String number, LocalDate date, String user, double amount, String counterparty, String currency, double exchangeRate, double commission) {
        super(number, date, user, amount);
        this.counterparty = counterparty;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "Заявка на оплату от " + date + " номер " + number;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
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

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
