module ru.documents.documents {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.documents.documents to javafx.fxml;
    exports ru.documents.documents;
}