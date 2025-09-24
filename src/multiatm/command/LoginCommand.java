package multiatm.command;

import multiatm.CashMachine;
import multiatm.ConsoleHelper;
import multiatm.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Logging in...");

        while (true) {
            ConsoleHelper.writeMessage("Please specify your credit card number and pin code or type 'EXIT' for exiting.");
            String creditCardNumber = ConsoleHelper.readString();
            String pinStr = ConsoleHelper.readString();
            if (!creditCardNumber.matches("\\d{12}") || !pinStr.matches("\\d{4}")) {
                ConsoleHelper.writeMessage("Invalid input. Card must be 12 digits, PIN — 4 digits.");
                continue;
            } else {
                    if (validCreditCards.containsKey(creditCardNumber) && pinStr.equals(validCreditCards.getString(creditCardNumber))) {
                        ConsoleHelper.writeMessage(String.format("Credit card [%s] is verified successfully!", creditCardNumber));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(String.format("Credit card [%s] is not verified.", creditCardNumber));
                        ConsoleHelper.writeMessage("Please try again or type 'EXIT' for urgent exiting");
                    }
            }
        }
    }
}
