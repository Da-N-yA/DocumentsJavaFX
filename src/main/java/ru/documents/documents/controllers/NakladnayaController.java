package ru.documents.documents.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.documents.documents.lists.Nakladnaya;


public class NakladnayaController {
    @FXML
    private TextField amountField;

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
    private TextField productField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField userField;

    @FXML
    void initialize() {
        createButton.setOnAction(event -> createInvoice());
    }
    private void createInvoice() {
        try {
            String number = numberField.getText();
            LocalDate date = dateField.getValue();
            String user = userField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String currency = currencyField.getText();
            double exchangeRate = Double.parseDouble(exchangeRateField.getText());
            String product = productField.getText();
            double quantity = Double.parseDouble(quantityField.getText());
            Nakladnaya newNakladnaya = new Nakladnaya(number, date, user, amount, currency, exchangeRate, product, quantity);
            DocumentController.addDocument(newNakladnaya);
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
    }

}
