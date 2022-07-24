package academy.pocu.comp2500.assignment4;

public class ClearCommand implements ICommand {
    private char[][] originCanvas;
    private Canvas inputCanvas;
    private boolean canUndo;

    public boolean execute(Canvas canvas) {
        if (this.originCanvas != null) {
            return false;
        }

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        this.originCanvas = new char[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                this.originCanvas[i][j] = canvas.getPixel(j, i);
            }
        }

        canvas.clear();
        this.canUndo = true;
        this.inputCanvas = canvas;

        return true;
    }

    public boolean undo() {
        if (!this.canUndo) {
            return false;
        }

        for (int i = 0; i < this.inputCanvas.getHeight(); ++i) {
            for (int j = 0; j < this.inputCanvas.getWidth(); ++j) {
                this.inputCanvas.drawPixel(j, i, this.originCanvas[i][j]);
            }
        }

        this.canUndo = false;

        return true;
    }

    public boolean redo() {
        if (this.canUndo || this.originCanvas == null) {
            return false;
        }

        this.inputCanvas.clear();
        this.canUndo = true;

        return true;
    }
}
