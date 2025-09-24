package multiatm;

import multiatm.command.CommandExecutor;
import multiatm.exception.InterruptOperationException;

public class CashMachine {
    public static void main(String[] args) {
        try {
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            ConsoleHelper.writeMessage("Thank you for visiting cash machine. Good luck.");
        }
    }
}
