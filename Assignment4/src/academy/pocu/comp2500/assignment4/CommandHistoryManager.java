package academy.pocu.comp2500.assignment4;

import java.util.Stack;

public class CommandHistoryManager {
    private final Canvas canvas;
    private final Stack<ICommand> undo;
    private final Stack<ICommand> redo;

    public CommandHistoryManager(final Canvas canvas) {
        this.canvas = canvas;
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public boolean execute(final ICommand command) {
        if (!command.execute(this.canvas)) {
            return false;
        }

        this.undo.push(command);
        this.redo.clear();

        return true;
    }

    public boolean canUndo() {
        return this.undo.size() > 0;
    }

    public boolean canRedo() {
        return this.redo.size() > 0;
    }

    public boolean undo() {
        if (!canUndo()) {
            return false;
        }

        ICommand command = this.undo.pop();

        command.undo();
        this.redo.push(command);

        return true;
    }

    public boolean redo() {
        if (!canRedo()) {
            return false;
        }

        ICommand command = this.redo.pop();

        command.redo();
        this.undo.push(command);

        return true;
    }
}
