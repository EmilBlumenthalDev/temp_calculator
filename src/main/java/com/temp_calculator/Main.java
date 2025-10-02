package com.temp_calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter tempc = new TemperatureConverter();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter temperature value (or type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program.");
                break;
            }

            try {
                double temp = Double.parseDouble(input);

                System.out.println("Choose conversion type:");
                System.out.println("1: Fahrenheit to Celsius");
                System.out.println("2: Celsius to Fahrenheit");
                System.out.println("3: Kelvin to Celsius");
                System.out.print("Enter choice (1/2/3): ");
                String choice = scanner.nextLine();

                double result;
                switch (choice) {
                    case "1":
                        result = tempc.fahrenheitToCelcius(temp);
                        System.out.printf("%.2f Fahrenheit is %.2f Celsius%n", temp, result);
                        break;
                    case "2":
                        result = tempc.celciusToFahrenheit(temp);
                        System.out.printf("%.2f Celsius is %.2f Fahrenheit%n", temp, result);
                        break;
                    case "3":
                        result = tempc.kelvinToCelcius(temp);
                        System.out.printf("%.2f Kelvin is %.2f Celsius%n", temp, result);
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        continue;
                }

                if (tempc.isExtremeTemperature(result)) {
                    System.out.println("Warning: The converted temperature is considered extreme!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);

        scanner.close();
    }
}
