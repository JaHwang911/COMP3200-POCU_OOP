package academy.pocu.comp2500.assignment4;

public class FillHorizontalLineCommand implements ICommand {
    private final int y;
    private final char character;
    private boolean canUndo;
    private Canvas canvas;

    public FillHorizontalLineCommand(int y, char character) {
        this.y = y;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (y < 0 || y >= canvas.getHeight()) {
            return false;
        }

        canvas.fillHorizontalLine(y, character);
        this.canvas = canvas;
        canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        canvas.fillHorizontalLine(y, ' ');
        canUndo = false;

        assert !canUndo;
        return true;
    }

    public boolean redo() {
        if (canUndo || canvas == null) {
            return false;
        }

        canvas.fillHorizontalLine(y, character);
        canUndo = true;

        assert canUndo;
        return true;
    }
}
