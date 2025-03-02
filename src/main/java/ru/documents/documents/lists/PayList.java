package ru.documents.documents.lists;

import java.time.LocalDate;

public class PayList extends Documents {
    private String employee;

    public PayList(String number, LocalDate date, String user, double amount, String employee) {
        super(number, date, user, amount);
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Платёжка от " + date + " номер " + number;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
