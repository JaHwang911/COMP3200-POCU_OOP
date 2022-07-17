package academy.pocu.comp2500.assignment4;

public class DrawPixelCommand implements ICommand {
    private Canvas canvas;
    private final int x;
    private final int y;
    private final char character;
    private boolean canUndo;

    public DrawPixelCommand(int x, int y, char character) {
        this.x = x;
        this.y = y;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return false;
        }

        this.canvas = canvas;

        canvas.drawPixel(x, y, character);
        canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        canvas.drawPixel(this.x, this.y, ' ');
        canUndo = false;

        assert !canUndo;
        return true;
    }

    public boolean redo() {
        if (canUndo || canvas == null) {
            return false;
        }

        canvas.drawPixel(x, y, character);
        canUndo = true;

        assert canUndo;
        return true;
    }
}
