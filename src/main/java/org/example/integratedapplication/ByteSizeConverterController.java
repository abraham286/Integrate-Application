package org.example.integratedapplication;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ByteSizeConverterController {
    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> fromUnitComboBox;

    @FXML
    private ComboBox<String> toUnitComboBox;

    @FXML
    private TextField resultField;

    @FXML
    public void initialize() {
        fromUnitComboBox.getItems().addAll("Bytes", "KB", "MB", "GB");
        toUnitComboBox.getItems().addAll("Bytes", "KB", "MB", "GB");
        fromUnitComboBox.setValue("Bytes");
        toUnitComboBox.setValue("KB");
    }

    @FXML
    private void handleConvert() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String fromUnit = fromUnitComboBox.getValue();
            String toUnit = toUnitComboBox.getValue();
            double valueInBytes = convertToBytes(inputValue, fromUnit);
            double result = convertFromBytes(valueInBytes, toUnit);
            resultField.setText(String.format("%.4f", result));
        } catch (NumberFormatException e) {
            showError("Invalid input! Please enter a valid number.");
        }
    }

    private double convertToBytes(double value, String unit) {
        switch (unit) {
            case "Bytes": return value;
            case "KB": return value * 1024;
            case "MB": return value * 1024 * 1024;
            case "GB": return value * 1024 * 1024 * 1024;
            default: return 0;
        }
    }

    private double convertFromBytes(double value, String unit) {
        switch (unit) {
            case "Bytes": return value;
            case "KB": return value / 1024;
            case "MB": return value / (1024 * 1024);
            case "GB": return value / (1024 * 1024 * 1024);
            default: return 0;
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


