package ru.documents.documents.controllers;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.documents.documents.lists.PayList;

public class PayListController {
    @FXML
    private TextField amountField;

    @FXML
    private Button createButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField employeeField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField userField;

    @FXML
    void initialize() {
        createButton.setOnAction(event -> createPayList());
    }

    private void createPayList() {
        try {
            String number = numberField.getText();
            LocalDate date = dateField.getValue();
            String user = userField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String employee = employeeField.getText();

            PayList newPayList = new PayList(number, date, user, amount, employee);
            DocumentController.addDocument(newPayList);

            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
    }
}
