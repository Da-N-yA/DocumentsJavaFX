package ru.documents.documents.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ru.documents.documents.lists.*;


public class DocumentDetailsController {
    @FXML
    private TextArea memoArea;

    public void setDocument(Documents document) {
        StringBuilder sb = new StringBuilder();

        sb.append("Номер: ").append(document.getNumber()).append("\n");
        sb.append("Дата: ").append(document.getDate()).append("\n");
        sb.append("Пользователь: ").append(document.getUser()).append("\n");
        sb.append("Сумма: ").append(document.getAmount()).append("\n");

        switch (document) {
            case Nakladnaya nakladnaya -> {
                sb.append("Тип: Накладная\n");
                sb.append("Валюта: ").append(nakladnaya.getCurrency()).append("\n");
                sb.append("Курс Валюты: ").append(nakladnaya.getExchangeRate()).append("\n");
                sb.append("Товар: ").append(nakladnaya.getProduct()).append("\n");
                sb.append("Количество: ").append(nakladnaya.getQuantity()).append("\n");
            }
            case PayList payList -> {
                sb.append("Тип: Платёжка\n");
                sb.append("Сотрудник: ").append(payList.getEmployee()).append("\n");
            }
            case PayOrder payOrder -> {
                sb.append("Тип: Заявка на оплату\n");
                sb.append("Контрагент: ").append(payOrder.getCounterparty()).append("\n");
                sb.append("Валюта: ").append(payOrder.getCurrency()).append("\n");
                sb.append("Курс Валюты: ").append(payOrder.getExchangeRate()).append("\n");
                sb.append("Комиссия: ").append(payOrder.getCommission()).append("\n");
            }
            default -> {
            }
        }
        memoArea.setText(sb.toString());
    }
}
