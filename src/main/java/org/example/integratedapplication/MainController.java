package org.example.integratedapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ComboBox<String> appSelector;

    @FXML
    private BorderPane rootLayout; // Ensure this matches the fx:id in the FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the ComboBox items
        appSelector.getItems().addAll("Base Converter", "Binary Arithmetic", "Byte Size Converter");

        // Handle selection changes
        appSelector.setOnAction(e -> {
            String selectedApp = appSelector.getValue();
            switch (selectedApp) {
                case "Base Converter":
                    loadView("BaseConverter.fxml");
                    break;
                case "Binary Arithmetic":
                    loadView("BinaryArithmetic.fxml");
                    break;
                case "Byte Size Converter":
                    loadView("ByteSizeConverter.fxml");
                    break;
                default:
                    break;
            }
        });
    }

    private void loadView(String fxmlFile) {
        try {
            // Load the FXML file and set it as the center of the BorderPane
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent view = loader.load();
            rootLayout.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}