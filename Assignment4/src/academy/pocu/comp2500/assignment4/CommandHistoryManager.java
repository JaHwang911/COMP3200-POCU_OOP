package academy.pocu.comp2500.assignment4;

import java.util.Stack;

public class CommandHistoryManager {
    private final Canvas canvas;
    private final Stack<ICommand> undo;
    private final Stack<ICommand> redo;
    private final char[][] managerCanvas;

    public CommandHistoryManager(final Canvas canvas) {
        this.canvas = canvas;
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        this.managerCanvas = new char[canvas.getHeight()][canvas.getWidth()];

        copyCanvas();
    }

    public boolean execute(final ICommand command) {
        if (this.undo.contains(command) || this.redo.contains(command) || !command.execute(this.canvas)) {
            return false;
        }

        this.undo.push(command);
        this.redo.clear();

        copyCanvas();

        return true;
    }

    public boolean canUndo() {
        return this.undo.size() > 0 && checkPixelSame();
    }

    public boolean canRedo() {
        return this.redo.size() > 0 && checkPixelSame();
    }

    public boolean undo() {
        if (!canUndo()) {
            return false;
        }

        ICommand command = this.undo.pop();

        command.undo();
        this.redo.push(command);

        copyCanvas();

        return true;
    }

    public boolean redo() {
        if (!canRedo()) {
            return false;
        }

        ICommand command = this.redo.pop();

        command.redo();
        this.undo.push(command);

        copyCanvas();

        return true;
    }

    private void copyCanvas() {
        for (int i = 0; i < this.canvas.getHeight(); ++i) {
            for (int j = 0; j < this.canvas.getWidth(); ++j) {
                this.managerCanvas[i][j] = this.canvas.getPixel(j, i);
            }
        }
    }

    private boolean checkPixelSame() {
        for (int i = 0; i < this.canvas.getHeight(); ++i) {
            for (int j = 0; j < this.canvas.getWidth(); ++j) {
                if (this.managerCanvas[i][j] != this.canvas.getPixel(j, i)) {
                    return false;
                }
            }
        }

        return true;
    }
}
