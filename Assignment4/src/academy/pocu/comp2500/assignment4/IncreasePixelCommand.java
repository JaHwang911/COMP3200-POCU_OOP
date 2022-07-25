package academy.pocu.comp2500.assignment4;

public class IncreasePixelCommand implements ICommand {
    private final int x;
    private final int y;
    private boolean canUndo;
    private char updatedCharacter;
    private Canvas canvas;

    public IncreasePixelCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean execute(Canvas canvas) {
        if (this.x < 0 || this.x >= canvas.getWidth() ||
                this.y < 0 || this.y >= canvas.getHeight() ||
                this.canvas != null) {
            return false;
        }

        canvas.increasePixel(this.x, this.y);

        this.canvas = canvas;
        this.canUndo = true;
        this.updatedCharacter = canvas.getPixel(this.x, this.y);

        return true;
    }

    public boolean undo() {
        if (!this.canUndo || this.canvas.getPixel(this.x, this.y) != this.updatedCharacter) {
            return false;
        }

        this.canvas.decreasePixel(this.x, this.y);
        this.canUndo = false;

        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.increasePixel(this.x, this.y);
        this.canUndo = true;

        return true;
    }
}
