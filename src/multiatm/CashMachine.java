package multiatm;

import multiatm.command.CommandExecutor;

public class CashMachine {
    public static void main(String[] args) {
        Operation operation;
        do {
            operation = ConsoleHelper.askOperation();
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}
