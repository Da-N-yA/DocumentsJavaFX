package ru.documents.documents.controllers;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.documents.documents.lists.PayOrder;

public class PayOrderController {
    @FXML
    private TextField amountField;

    @FXML
    private TextField commissionField;

    @FXML
    private TextField counterpartyField;

    @FXML
    private Button createButton;

    @FXML
    private TextField currencyField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField exchangeRateField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField userField;

    @FXML
    void initialize() {
        createButton.setOnAction(event -> createPayOrder());
    }

    private void createPayOrder() {
        try {
            String number = numberField.getText();
            LocalDate date = dateField.getValue();
            String user = userField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String counterparty = counterpartyField.getText();
            String currency = currencyField.getText();
            double exchangeRate = Double.parseDouble(exchangeRateField.getText());
            double commission = Double.parseDouble(commissionField.getText());

            PayOrder newPayOrder = new PayOrder(number, date, user, amount, counterparty, currency, exchangeRate, commission);
            DocumentController.addDocument(newPayOrder);

            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
    }
}
