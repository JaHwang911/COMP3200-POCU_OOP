package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistoryManager {
    private final Canvas canvas;
    private final Stack<ICommand> executes;

    public CommandHistoryManager(Canvas canvas) {
        this.canvas = canvas;
        this.executes = new Stack<>();
    }

    public boolean execute(ICommand command) {
        if (!command.execute(this.canvas)) {
            return false;
        }

        this.executes.push(command);
        return true;
    }

    public boolean canUndo() {
        return this.executes.size() > 0;
    }
}
