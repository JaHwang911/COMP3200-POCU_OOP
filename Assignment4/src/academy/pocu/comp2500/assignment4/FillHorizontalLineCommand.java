package academy.pocu.comp2500.assignment4;

public class FillHorizontalLineCommand implements ICommand {
    private final int y;
    private final char character;
    private boolean canUndo;
    private Canvas canvas;

    public FillHorizontalLineCommand(final int y, final char character) {
        this.y = y;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (this.y < 0 || this.y >= canvas.getHeight()) {
            return false;
        }

        canvas.fillHorizontalLine(this.y, this.character);
        this.canvas = canvas;
        this.canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!this.canUndo) {
            return false;
        }

        this.canvas.fillHorizontalLine(this.y, ' ');
        this.canUndo = false;

        assert !this.canUndo;
        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.fillHorizontalLine(this.y, this.character);
        this.canUndo = true;

        assert this.canUndo;
        return true;
    }
}
