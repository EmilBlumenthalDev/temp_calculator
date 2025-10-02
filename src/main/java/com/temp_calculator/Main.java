package com.temp_calculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private TextField celsiusField = new TextField();
    private Label resultLabel = new Label();
    private ComboBox<String> conversionDropdown = new ComboBox<>();
    private double convertedValue;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TemperatureConverter tempc = new TemperatureConverter();
        celsiusField.setPromptText("Enter Celsius");

        conversionDropdown.getItems().addAll("Celsius to Fahrenheit", "Celsius to Kelvin");
        conversionDropdown.setValue("Celsius to Fahrenheit");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> {
            try {
                double celsius = Double.parseDouble(celsiusField.getText());
                String selectedConversion = conversionDropdown.getValue();
                
                if ("Celsius to Fahrenheit".equals(selectedConversion)) {
                    convertedValue = tempc.celciusToFahrenheit(celsius);
                    resultLabel.setText(String.format("%.2f°C = %.2f°F", celsius, convertedValue));
                } else if ("Celsius to Kelvin".equals(selectedConversion)) {
                    convertedValue = tempc.celciusToKelvin(celsius);
                    resultLabel.setText(String.format("%.2f°C = %.2f K", celsius, convertedValue));
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number");
            }
        });

        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> Database.saveTemperature(
                Double.parseDouble(celsiusField.getText()), convertedValue, resultLabel));

        VBox root = new VBox(10, celsiusField, conversionDropdown, convertButton, resultLabel, saveButton);
        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.show();
    }
}