package multiatm.command;

import multiatm.ConsoleHelper;
import multiatm.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private String validCreditCard = "123456789012";
    private String validPin = "1234";

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Logging in...");

        while (true) {
            ConsoleHelper.writeMessage("Enter 12-digit credit card number:");
            String card = ConsoleHelper.readString().trim();

            ConsoleHelper.writeMessage("Enter 4-digit PIN code:");
            String pin = ConsoleHelper.readString().trim();

            if (!card.matches("\\d{12}") || !pin.matches("\\d{4}")) {
                ConsoleHelper.writeMessage("Invalid input. Card must be 12 digits, PIN — 4 digits.");
                continue;
            }

            if (card.equals(validCreditCard) && pin.equals(validPin)) {
                ConsoleHelper.writeMessage(String.format("Credit card [%s] verified successfully!", card));
                break;
            } else {
                ConsoleHelper.writeMessage(String.format("Credit card [%s] is not verified.", card));
                ConsoleHelper.writeMessage("Try again or type 'EXIT' to quit.");
            }
        }
    }
}
