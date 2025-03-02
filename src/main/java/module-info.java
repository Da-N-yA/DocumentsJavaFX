module ru.documents.documents {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.documents.documents to javafx.fxml;
    exports ru.documents.documents;
    exports ru.documents.documents.controllers;
    opens ru.documents.documents.controllers to javafx.fxml;
    exports ru.documents.documents.lists;
    opens ru.documents.documents.lists to javafx.fxml;
}