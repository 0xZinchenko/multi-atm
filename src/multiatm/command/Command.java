package multiatm.command;

import multiatm.exception.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
