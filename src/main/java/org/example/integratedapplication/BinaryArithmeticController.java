package org.example.integratedapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class BinaryArithmeticController implements Initializable {

    @FXML
    private ComboBox<String> comb1;
    @FXML
    private ComboBox<String> comb2;
    @FXML
    private TextField textInput;
    @FXML
    private TextField textOutput;

    private final ObservableList<String> numberBases = FXCollections.observableArrayList(
            "binary", "decimal", "hexadecimal", "octal"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comb1.setItems(numberBases);
        comb2.setItems(numberBases);
        comb1.setValue("binary");
        comb2.setValue("decimal");

        // Set converter for uppercase hexadecimal display
        comb2.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return object.substring(0, 1).toUpperCase() + object.substring(1);
            }

            @Override
            public String fromString(String string) {
                return string.toLowerCase();
            }
        });
    }

    @FXML
    private void handleConvert() {
        String input = textInput.getText().trim();
        String fromBase = comb1.getValue();
        String toBase = comb2.getValue();

        if (input.isEmpty()) {
            showError("Input Error", "Please enter a number to convert");
            return;
        }

        if (!isValidInput(input, fromBase)) {
            showError("Invalid Input", "Input does not match selected base: " + fromBase);
            return;
        }

        try {
            String result = convertNumber(fromBase, toBase, input);
            textOutput.setText(result);
        } catch (NumberFormatException e) {
            showError("Conversion Error", "Invalid number format: " + e.getMessage());
        }
    }

    private String convertNumber(String fromBase, String toBase, String input) {
        int decimalValue = switch (fromBase.toLowerCase()) {
            case "binary" -> Integer.parseInt(input, 2);
            case "decimal" -> Integer.parseInt(input);
            case "octal" -> Integer.parseInt(input, 8);
            case "hexadecimal" -> Integer.parseInt(input.toUpperCase(), 16);
            default -> throw new IllegalArgumentException("Invalid base");
        };

        return switch (toBase.toLowerCase()) {
            case "binary" -> Integer.toBinaryString(decimalValue);
            case "decimal" -> Integer.toString(decimalValue);
            case "octal" -> Integer.toOctalString(decimalValue);
            case "hexadecimal" -> Integer.toHexString(decimalValue).toUpperCase();
            default -> throw new IllegalArgumentException("Invalid target base");
        };
    }

    private boolean isValidInput(String input, String base) {
        return switch (base.toLowerCase()) {
            case "binary" -> input.matches("[01]+");
            case "decimal" -> input.matches("\\d+");
            case "octal" -> input.matches("[0-7]+");
            case "hexadecimal" -> input.matches("[0-9A-Fa-f]+");
            default -> false;
        };
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}