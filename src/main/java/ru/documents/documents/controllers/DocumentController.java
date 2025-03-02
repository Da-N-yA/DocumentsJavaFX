package ru.documents.documents.controllers;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.documents.documents.lists.Documents;
import ru.documents.documents.lists.Nakladnaya;
import ru.documents.documents.lists.PayList;
import ru.documents.documents.lists.PayOrder;

public class DocumentController {
    private static final ObservableList<Documents> documentsObservableList = FXCollections.observableArrayList();

    @FXML
    private Button documentExit, documentLoad, documentNakladnaya, documentPayList, documentPayOrder, documentSave, documentWatch, documentDelete;

    @FXML
    private ListView<Documents> documentsListView;

    public static void addDocument(Documents doc) {
        documentsObservableList.add(doc);
    }

    @FXML
    void initialize() {
        documentsListView.setItems(documentsObservableList);
        setButtonActions();
        documentsListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    }

    private void setButtonActions() {
        documentNakladnaya.setOnAction(event -> loadFXML("/ru/documents/documents/nakladnaya.fxml"));
        documentPayList.setOnAction(event -> loadFXML("/ru/documents/documents/payList.fxml"));
        documentPayOrder.setOnAction(event -> loadFXML("/ru/documents/documents/payOrder.fxml"));
        documentSave.setOnAction(event -> saveDocumentsToFile());
        documentLoad.setOnAction(event -> loadDocumentsFromFile());
        documentWatch.setOnAction(event -> openDocumentDetails());
        documentDelete.setOnAction(event -> deleteSelectedDocument());
        documentExit.setOnAction(event -> closeWindow());
    }

    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Создание нового документа");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            showErrorAlert("Ошибка при загрузке документа", e);
        }
    }

    private void openDocumentDetails() {
        Documents selectedDocument = documentsListView.getSelectionModel().getSelectedItem();
        if (selectedDocument != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/documents/documents/documentDetails.fxml"));
                Parent root = loader.load();
                DocumentDetailsController controller = loader.getController();
                controller.setDocument(selectedDocument);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Просмотр документа");
                stage.show();
            } catch (IOException e) {
                showErrorAlert("Ошибка при открытии документа", e);
            }
        }
    }

    private void deleteSelectedDocument() {
        ObservableList<Documents> selectedDocuments = documentsListView.getSelectionModel().getSelectedItems();

        if (!selectedDocuments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Вы уверены, что хотите удалить выбранный(е) документ(ы)?");
            alert.setContentText("Будет удалено " + selectedDocuments.size() + " документ(ов).");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                documentsObservableList.removeAll(selectedDocuments);
                System.out.println("Удален(ы) документ(ы): " + selectedDocuments);
            }
        } else {
            System.out.println("Документ не выбран!");
        }
    }


    private void closeWindow() {
        Stage stage = (Stage) documentExit.getScene().getWindow();
        stage.close();
    }

    private void saveDocumentsToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Documents doc : documentsObservableList) {
                    writer.write(getDocumentData(doc));
                    writer.newLine();
                }
            } catch (IOException e) {
                showErrorAlert("Ошибка при сохранении документов", e);
            }
        }
    }

    private String getDocumentData(Documents doc) {
        if (doc instanceof Nakladnaya nakladnaya) {
            return String.format("N;%s;%s;%s;%.2f;%s;%.2f;%s;%.2f", nakladnaya.getNumber(), nakladnaya.getDate(),
                    nakladnaya.getUser(), nakladnaya.getAmount(), nakladnaya.getCurrency(), nakladnaya.getExchangeRate(),
                    nakladnaya.getProduct(), nakladnaya.getQuantity());
        } else if (doc instanceof PayList payList) {
            return String.format("P;%s;%s;%s;%.2f;%s", payList.getNumber(), payList.getDate(), payList.getUser(),
                    payList.getAmount(), payList.getEmployee());
        } else if (doc instanceof PayOrder payOrder) {
            return String.format("O;%s;%s;%s;%s;%.2f;%s;%.2f;%.2f", payOrder.getNumber(), payOrder.getDate(),
                    payOrder.getUser(), payOrder.getCounterparty(), payOrder.getAmount(), payOrder.getCurrency(),
                    payOrder.getExchangeRate(), payOrder.getCommission());
        }
        return "";
    }

    private void loadDocumentsFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    createDocumentFromLine(line);
                }
            } catch (IOException e) {
                showErrorAlert("Ошибка при загрузке документов", e);
            }
        }
    }

    private void createDocumentFromLine(String line) {
        String[] parts = line.split(";");
        if (parts.length > 0) {
            switch (parts[0]) {
                case "N":
                    if (parts.length == 9) {
                        Nakladnaya nakladnaya = new Nakladnaya(parts[1], LocalDate.parse(parts[2]), parts[3],
                                Double.parseDouble(parts[4]), parts[5], Double.parseDouble(parts[6]),
                                parts[7], Double.parseDouble(parts[8]));
                        documentsObservableList.add(nakladnaya);
                    }
                    break;
                case "P":
                    if (parts.length == 6) {
                        PayList payList = new PayList(parts[1], LocalDate.parse(parts[2]), parts[3],
                                Double.parseDouble(parts[4]), parts[5]);
                        documentsObservableList.add(payList);
                    }
                    break;
                case "O":
                    if (parts.length == 9) {
                        PayOrder payOrder = new PayOrder(parts[1], LocalDate.parse(parts[2]), parts[3],
                                Double.parseDouble(parts[5]), parts[4], parts[6], Double.parseDouble(parts[7]),
                                Double.parseDouble(parts[8]));
                        documentsObservableList.add(payOrder);
                    }
                    break;
            }
        }
    }

    private void showErrorAlert(String message, Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(message);
        alert.setContentText("Ошибка: " + e.getMessage());
        alert.showAndWait();
    }
}
