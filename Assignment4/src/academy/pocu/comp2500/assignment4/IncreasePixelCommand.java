package academy.pocu.comp2500.assignment4;

public class IncreasePixelCommand implements ICommand {
    private final int x;
    private final int y;
    private boolean canUndo;
    private Canvas canvas;

    public IncreasePixelCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean execute(Canvas canvas) {
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return false;
        }

        this.canvas = canvas;
        canUndo = true;

        return canvas.increasePixel(x, y);
    }

    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        canvas.decreasePixel(x, y);
        canUndo = false;

        assert !canUndo;
        return true;
    }

    public boolean redo() {
        if (canUndo || canvas == null) {
            return false;
        }

        canvas.increasePixel(x, y);
        canUndo = true;

        assert canUndo;
        return true;
    }
}
