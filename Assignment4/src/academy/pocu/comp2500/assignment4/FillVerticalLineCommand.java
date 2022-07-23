package academy.pocu.comp2500.assignment4;

public class FillVerticalLineCommand implements ICommand {
    private final int x;
    private final char character;
    private boolean canUndo;
    private Canvas canvas;

    public FillVerticalLineCommand(final int x, final char character) {
        this.x = x;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (this.x < 0 || this.x >= canvas.getWidth()) {
            return false;
        }

        canvas.fillVerticalLine(this.x, this.character);
        this.canvas = canvas;
        this.canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!this.canUndo) {
            return false;
        }

        this.canvas.fillVerticalLine(this.x, ' ');
        this.canUndo = false;

        assert !this.canUndo;
        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.fillVerticalLine(this.x, this.character);
        this.canUndo = true;

        assert this.canUndo;
        return true;
    }
}
