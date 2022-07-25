package academy.pocu.comp2500.assignment4;

public class DrawPixelCommand implements ICommand {
    private final int x;
    private final int y;
    private final char character;
    private char originCharacter;
    private boolean canUndo;
    private Canvas canvas;

    public DrawPixelCommand(int x, int y, char character) {
        this.x = x;
        this.y = y;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (this.x < 0 || this.x >= canvas.getWidth() ||
                this.y < 0 || this.y >= canvas.getHeight() ||
                this.canvas != null) {
            return false;
        }

        this.canvas = canvas;
        this.originCharacter = canvas.getPixel(this.x, this.y);

        canvas.drawPixel(this.x, this.y, this.character);
        this.canUndo = true;

        return true;
    }

    public boolean undo() {
        if (!this.canUndo || this.canvas.getPixel(this.x, this.y) != this.character) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.originCharacter);
        this.canUndo = false;

        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.character);
        this.canUndo = true;

        return true;
    }
}
