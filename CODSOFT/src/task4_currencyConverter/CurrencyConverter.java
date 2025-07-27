package task4_currencyConverter;

// Import necessary classes for HTTP request, JSON parsing, and user input
import java.net.HttpURLConnection;
import java.net.URL;
import  org.json.JSONObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


// CurrencyConverter class definition
public class CurrencyConverter {
    // Static Scanner for user input
    static Scanner sc = new Scanner(System.in);

    // Member variables for base currency, target currency, and amount to convert
    String baseCurrency = null;
    String targetCurrency = null;
    double amount = 0;

    // Method to get real-time exchange rate between base and target currency using an API
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        // API endpoint with base currency
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;

        try {
            // Create URL object and open a connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check if connection is successful
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed to connect. HTTP Code: " + responseCode);
                return -1;
            }

            // Read the API response using Scanner
            Scanner responseScanner = new Scanner(url.openStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (responseScanner.hasNext()) {
                responseBuilder.append(responseScanner.nextLine());
            }
            responseScanner.close(); // Close Scanner

            // Convert response to JSON and extract rates
            JSONObject jsonData = new JSONObject(responseBuilder.toString());
            JSONObject rateObject = jsonData.getJSONObject("rates");

            // Return exchange rate if target currency is found
            if (rateObject.has(targetCurrency)) {
                return rateObject.getDouble(targetCurrency);
            } else {
                System.out.println("Sorry, exchange rate for " + targetCurrency + " is not available.");
                return -1;
            }

        } catch (Exception e) {
            // Handle exceptions such as invalid URL or no internet
            System.out.println("An error occurred while retrieving exchange rates: " + e.getMessage());
            return -1;
        }
    }

    // Main method that handles currency conversion process
    public void currencyConverter(){
        // Define valid currency codes allowed for conversion
        Set<String> validCurrencies = new HashSet<>(Arrays.asList("INR", "USD", "EUR", "GBP", "JPY", "AUD", "CAD"));

        // Display available currencies
        System.out.println("\nAvailable currencies: INR, USD, EUR, GBP, JPY, AUD, CAD");

        // Get base currency from user
        System.out.println("Enter the base currency: ");
        while (true) {
            baseCurrency = sc.nextLine().toUpperCase(); // Convert to uppercase

            // Validate base currency input
            if (baseCurrency.isEmpty()){
                System.out.println("Base currency cannot be empty! Please enter a valid currency code: ");
            }
            else if ((baseCurrency.matches("[A-Z]{3}"))) {
                if (validCurrencies.contains(baseCurrency)){
                    break;
                }
                else {
                    System.out.println("Invalid currency code! Please enter one of the available options: ");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a currency code from the available options:");
            }
        }

        // Get target currency from user
        System.out.println("\nEnter the target currency: ");
        while (true) {
            targetCurrency = sc.nextLine().toUpperCase(); // Convert to uppercase

            // Validate target currency input
            if (targetCurrency.isEmpty()){
                System.out.println("Target currency cannot be empty! Please enter a valid currency code: ");
            }
            else if ((targetCurrency.matches("[A-Z]{3}"))) {
                if (validCurrencies.contains(targetCurrency)){
                    break;
                }
                else {
                    System.out.println("Invalid currency code! Please enter one of the available options: ");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a currency code from the available options:");
            }
        }

        // Prompt user to enter amount to convert
        System.out.println("\nEnter the amount to convert: ");
        while (true){
            if (sc.hasNextDouble()){
                amount = sc.nextDouble();
                sc.nextLine(); // Consume newline

                // Validate amount
                if (amount <= 0){
                    System.out.println("Amount must be greater than 0! Please enter a valid number: ");
                }
                else {
                    break;
                }
            }
            else {
                System.out.println("Invalid input! Amount must contains only numbers.");
                System.out.println("Please enter a valid amount: ");
                sc.nextLine(); // Clear invalid input
            }
        }

        // Handle conversion if both currencies are the same
        if (baseCurrency.equals(targetCurrency)){
            System.out.printf("\n%.2f %s = %.2f %s\n",amount,baseCurrency,amount,targetCurrency);
        }
        else {
            // Call API and get exchange rate
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            if (exchangeRate != -1){
                double convertedRate = amount * exchangeRate;
                System.out.printf("\n%.2f %s = %.2f %s\n",amount,baseCurrency,convertedRate,targetCurrency);
            }
        }

        // Ask user if they want to convert again
        System.out.println("\nWould you like to convert again? Type 'yes' or 'no':");
    }

    // Main method: program execution starts here
    public static void main(String[] args) {
        System.out.println("----- Currency Converter -----");

        // Create an instance of CurrencyConverter
        CurrencyConverter converter = new CurrencyConverter();

        // Greet the user
        System.out.println("\nHello user! Welcome to the Currency Converter.\nYou can convert from one currency to another using real-time exchange rates.");

        // Start the first conversion
        converter.currencyConverter();

        // Loop to allow repeated conversions
        while (true){
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("yes")){
                System.out.println("\nConverting currency again...");
                converter.currencyConverter(); // Call again
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("Thanks for using our currency converter. Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid input! Please enter 'yes' or 'no':");
            }
        }
    }
}
