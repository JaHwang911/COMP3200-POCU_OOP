package academy.pocu.comp2500.assignment4;

public class FillHorizontalLineCommand implements ICommand {
    private final int y;
    private final char character;
    private boolean canUndo;
    private Canvas canvas;
    private char[] originCharacters;
    private char[] updatedCharacters;

    public FillHorizontalLineCommand(final int y, final char character) {
        this.y = y;
        this.character = character;
    }

    @Override
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

        this.updatedCharacters = new char[canvas.getWidth()];

        for (int i = 0; i < canvas.getWidth(); ++i) {
            this.updatedCharacters[i] = canvas.getPixel(i, this.y);
        }

        return true;
    }

    @Override
    public boolean undo() {
        if (!this.canUndo) {
            return false;
        }

        for (int i = 0; i < this.canvas.getWidth(); ++i) {
            if (this.canvas.getPixel(i, this.y) != this.updatedCharacters[i]) {
                return false;
            }
        }

        for (int i = 0; i < this.canvas.getWidth(); ++i) {
            this.canvas.drawPixel(i, this.y, this.originCharacters[i]);
        }

        this.canUndo = false;

        return true;
    }

    @Override
    public boolean redo() {
        if (this.canUndo || this.canvas == null) {
            return false;
        }

        for (int i = 0; i < canvas.getWidth(); ++i) {
            if (this.originCharacters[i] != this.canvas.getPixel(i, this.y)) {
                return false;
            }
        }

        this.canvas.fillHorizontalLine(this.y, this.character);
        this.canUndo = true;

        return true;
    }
}
