package multiatm.command;

import multiatm.CashMachine;
import multiatm.ConsoleHelper;
import multiatm.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class ExitCommand implements Command  {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.exit");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String result = ConsoleHelper.readString();
        if (result != null && "y".equals(result.toLowerCase())) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        } else {}
    }
}
