package org.example.integratedapplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class BaseConverterController {

    @FXML
    private TextField inputField; // TextField for user input

    @FXML
    private ComboBox<String> fromUnit; // ComboBox for selecting the "from" unit

    @FXML
    private ComboBox<String> toUnit; // ComboBox for selecting the "to" unit

    @FXML
    private void initialize() {
        // Initialize the ComboBoxes with options
        fromUnit.getItems().addAll("Binary", "Decimal", "Hexadecimal", "Octal");
        toUnit.getItems().addAll("Binary", "Decimal", "Hexadecimal", "Octal");

        // Set default selections
        fromUnit.setValue("Binary");
        toUnit.setValue("Decimal");
    }

    @FXML
    private void handleConvert(ActionEvent event) {
        try {
            // Get user input and selected units
            String input = inputField.getText().trim();
            String from = fromUnit.getValue();
            String to = toUnit.getValue();

            // Validate input
            if (input.isEmpty()) {
                showError("Input cannot be empty!");
                return;
            }

            // Perform the conversion
            String result = convert(input, from, to);

            // Display the result (you can use a Label or another TextField)
            System.out.println("Result: " + result);
            showAlert("Conversion Result", "The result is: " + result);
        } catch (NumberFormatException e) {
            showError("Invalid input! Please enter a valid number.");
        } catch (Exception e) {
            showError("An error occurred during conversion.");
        }
    }
    private String convert(String input, String from, String to) {
        int decimalValue;

        // Convert the input to a decimal (base 10) value
        switch (from) {
            case "Binary":
                decimalValue = Integer.parseInt(input, 2);
                break;
            case "Decimal":
                decimalValue = Integer.parseInt(input, 10);
                break;
            case "Hexadecimal":
                decimalValue = Integer.parseInt(input, 16);
                break;
            case "Octal":
                decimalValue = Integer.parseInt(input, 8);
                break;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit: " + from);
        }

        // Convert the decimal value to the target base
        switch (to) {
            case "Binary":
                return Integer.toBinaryString(decimalValue);
            case "Decimal":
                return Integer.toString(decimalValue);
            case "Hexadecimal":
                return Integer.toHexString(decimalValue).toUpperCase();
            case "Octal":
                return Integer.toOctalString(decimalValue);
            default:
                throw new IllegalArgumentException("Invalid 'to' unit: " + to);
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
