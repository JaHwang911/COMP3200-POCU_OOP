package academy.pocu.comp2500.assignment4;

public class FillHorizontalLineCommand implements ICommand {
    private final int y;
    private final char character;
    private boolean canUndo;
    private Canvas canvas;
    private char[] originCharacters;

    public FillHorizontalLineCommand(final int y, final char character) {
        this.y = y;
        this.character = character;
    }

    public boolean execute(Canvas canvas) {
        if (this.y < 0 || this.y >= canvas.getHeight() || this.canvas != null) {
            return false;
        }

        this.originCharacters = new char[canvas.getWidth()];

        for (int i = 0; i < canvas.getWidth(); ++i) {
            this.originCharacters[i] = canvas.getPixel(i, this.y);
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

        for (int i = 0; i < this.canvas.getWidth(); ++i) {
            this.canvas.drawPixel(i, this.y, this.originCharacters[i]);
        }

        this.canUndo = false;

        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        this.canvas.fillHorizontalLine(this.y, this.character);
        this.canUndo = true;

        return true;
    }
}
