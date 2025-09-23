package multiatm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return bis.readLine();
        } catch (IOException ignored) {
        }
        return null;
    }

    public static String askCurrencyCode() {
        while (true) {
            writeMessage("Please enter a 3-letter currency code, for example USD:");
            String input = readString();
            if (input != null) {
                input = input.trim();
                if (input.length() == 3) {
                    return input.toUpperCase();
                }
            }
            writeMessage("Invalid input. Currency code must be exactly 3 characters. Try again.");
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        while (true) {
            writeMessage("Enter two positive integers separated by space. " +
                    "First is denomination, second is number of banknotes:");
            String input = readString().trim();
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                writeMessage("Invalid input. You must enter exactly two numbers.");
                continue;
            }

            try {
                int denomination = Integer.parseInt(parts[0]);
                int banknotes = Integer.parseInt(parts[1]);

                if (denomination > 0 && banknotes > 0) {
                    return new String[] { String.valueOf(denomination), String.valueOf(banknotes)};
                } else {
                    writeMessage("Both numbers must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                writeMessage("Invalid number format. Please enter integers only.");
            }


        }
    }
}
