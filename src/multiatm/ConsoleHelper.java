package multiatm;

import multiatm.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
       try {
           String text = bis.readLine();
           if ("exit".equals(text.toLowerCase())) {
               throw new InterruptOperationException();
           }
           return text;

       } catch (IOException ignored) {}
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String input = readString();
            if (input != null) {
                input = input.trim();
                if (input.length() == 3) {
                    return input.toUpperCase();
                }
            }
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.denomination.and.count.format"));
            String input = readString().trim();
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }

            try {
                int denomination = Integer.parseInt(parts[0]);
                int banknotes = Integer.parseInt(parts[1]);

                if (denomination > 0 && banknotes > 0) {
                    return new String[] { String.valueOf(denomination), String.valueOf(banknotes)};
                } else {
                    writeMessage(res.getString("invalid.data"));
                }
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.operation"));
            ConsoleHelper.writeMessage("\t 1 - " + res.getString("operation.INFO"));
            ConsoleHelper.writeMessage("\t 2 - " + res.getString("operation.DEPOSIT"));
            ConsoleHelper.writeMessage("\t 3 - " + res.getString("operation.WITHDRAW"));
            ConsoleHelper.writeMessage("\t 4 - " + res.getString("operation.EXIT"));
            Integer i = Integer.parseInt(readString().trim());
            try {
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
