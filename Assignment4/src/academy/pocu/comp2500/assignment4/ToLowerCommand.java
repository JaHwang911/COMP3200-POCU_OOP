package academy.pocu.comp2500.assignment4;

public class ToLowerCommand implements ICommand {
    private final int x;
    private final int y;
    private boolean canUndo;
    private Canvas canvas;

    public ToLowerCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean execute(Canvas canvas) {
        if (this.x < 0 || this.x >= canvas.getWidth() ||
                this.y < 0 || this.y >= canvas.getHeight() ||
                this.canvas != null) {
            return false;
        }

        char origin = canvas.getPixel(this.x, this.y);

        if (origin < 'A' || origin > 'Z') {
            return false;
        }

        canvas.toLower(this.x, this.y);
        this.canvas = canvas;
        this.canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!this.canUndo) {
            return false;
        }

        this.canvas.toUpper(this.x, this.y);
        this.canUndo = false;

        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.toLower(this.x, this.y);
        this.canUndo = true;

        return true;
    }
}