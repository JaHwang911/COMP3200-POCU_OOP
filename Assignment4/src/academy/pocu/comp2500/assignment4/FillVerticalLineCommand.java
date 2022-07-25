package academy.pocu.comp2500.assignment4;

public class FillVerticalLineCommand implements ICommand {
    private final int x;
    private final char character;
    private boolean canUndo;
    private Canvas canvas;
    private char[] originCharacters;
    private char[] updatedCharacters;

    public FillVerticalLineCommand(final int x, final char character) {
        this.x = x;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (this.x < 0 || this.x >= canvas.getWidth() || this.canvas != null) {
            return false;
        }

        this.originCharacters = new char[canvas.getHeight()];

        for (int i = 0; i < canvas.getHeight(); ++i) {
            this.originCharacters[i] = canvas.getPixel(this.x, i);
        }

        canvas.fillVerticalLine(this.x, this.character);
        this.canvas = canvas;
        this.canUndo = true;

        this.updatedCharacters = new char[canvas.getHeight()];

        for (int i = 0; i < canvas.getHeight(); ++i) {
            this.updatedCharacters[i] = canvas.getPixel(this.x, i);
        }

        return true;
    }

    public boolean undo() {
        if (!this.canUndo) {
            return false;
        }

        for (int i = 0; i < this.canvas.getHeight(); ++i) {
            if (this.updatedCharacters[i] != this.canvas.getPixel(this.x, i)) {
                return false;
            }
        }

        for (int i = 0; i < this.canvas.getHeight(); ++i) {
            this.canvas.drawPixel(this.x, i, this.originCharacters[i]);
        }

        this.canUndo = false;

        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.fillVerticalLine(this.x, this.character);
        this.canUndo = true;

        return true;
    }
}
