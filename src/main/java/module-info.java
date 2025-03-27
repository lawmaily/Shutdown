module com.example.shutdown {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    exports com.example.shutdown.services;
    opens com.example.shutdown to javafx.fxml;
    exports com.example.shutdown;
    exports com.example.shutdown.controllers;
    opens com.example.shutdown.controllers to javafx.fxml;
}