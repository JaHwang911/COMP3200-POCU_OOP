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
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return false;
        }

        char origin = canvas.getPixel(x, y);

        if (origin < 'A' || origin > 'Z') {
            return false;
        }

        canvas.toLower(x, y);
        this.canvas = canvas;
        canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        canvas.toUpper(x, y);
        canUndo = false;

        assert !canUndo;
        return true;
    }

    public boolean redo() {
        if (canUndo || canvas == null) {
            return false;
        }

        canvas.toLower(x, y);
        canUndo = true;

        assert canUndo;
        return true;
    }
}
