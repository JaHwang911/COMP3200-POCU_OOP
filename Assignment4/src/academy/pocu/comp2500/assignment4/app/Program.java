package academy.pocu.comp2500.assignment4.app;

import academy.pocu.comp2500.assignment4.*;

public class Program {

    public static void main(String[] args) {
        testCanvas();
//        testCommandHistoryManager0();

        System.out.println("No prob: assignment4");
    }

    private static void testCanvas() {
        Canvas canvas = new Canvas(10, 10);
        canvas.drawPixel(0, 0, '*');
        canvas.drawPixel(1, 2, '$');
        canvas.drawPixel(0, 1, '&');
        canvas.drawPixel(7, 7, '~');
        canvas.drawPixel(9, 9, 'a');

        // Non-drawn characters
        canvas.drawPixel(10, 10, 'X');
        canvas.drawPixel(-1, -1, 'X');
        canvas.drawPixel(11, 11, 'X');

        assert (canvas.getPixel(0, 0) == '*');
        assert (canvas.getPixel(1, 2) == '$');
        assert (canvas.getPixel(0, 1) == '&');
        assert (canvas.getPixel(7, 7) == '~');
        assert (canvas.getPixel(9, 9) == 'a');

        assert !canvas.increasePixel(10, 10);
        assert !canvas.increasePixel(7, 7);
        assert !canvas.decreasePixel(8, 8);

        assert canvas.increasePixel(9, 9);
        assert (canvas.getPixel(9, 9) == 'b');

        assert canvas.decreasePixel(9, 9);
        assert (canvas.getPixel(9, 9) == 'a');

        canvas.fillHorizontalLine(0, '$');

        for (int i = 0; i < 10; ++i) {
            assert canvas.getPixel(i, 0) == '$';
        }

        canvas.fillVerticalLine(0, '*');

        for (int i = 0; i < 10; ++i) {
            assert canvas.getPixel(0, i) == '*';
        }

        // Non-drawn characters
        canvas.fillHorizontalLine(-1, 'X');
        canvas.fillHorizontalLine(10, 'X');
        canvas.fillVerticalLine(-1, 'X');
        canvas.fillVerticalLine(10, 'X');

        canvas.toUpper(9, 9);
        assert (canvas.getPixel(9, 9) == 'A');

        canvas.toLower(9, 9);
        assert (canvas.getPixel(9, 9) == 'a');

        canvas.toUpper(0, 0);
        assert (canvas.getPixel(0, 0) == '*');

        canvas.toLower(0, 1);
        assert (canvas.getPixel(1, 0) == '$');

        System.out.println(canvas.getDrawing());

        canvas.clear();
        assert testClearCanvas(canvas);

        System.out.println(canvas.getDrawing());
    }

    private static void testCommandHistoryManager0() {
        Canvas canvas = new Canvas(10, 10);
        CommandHistoryManager manager = new CommandHistoryManager(canvas);

        manager.execute(new DrawPixelCommand(0, 0, 'a'));
        manager.execute(new FillHorizontalLineCommand(0, 'a'));
        manager.undo();

        assert !manager.canUndo();
        assert !manager.canRedo();
        assert !manager.undo();
        assert !manager.redo();

        assert manager.execute(new DrawPixelCommand(0, 0, 'a'));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == ' ');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        assert manager.execute(new IncreasePixelCommand(0, 0));
        assert (canvas.getPixel(0, 0) == 'b');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'b');

        assert manager.execute(new DecreasePixelCommand(0, 0));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'b');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        assert manager.execute(new ToUpperCommand(0, 0));
        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'A');

        assert manager.execute(new ToLowerCommand(0, 0));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        assert manager.execute(new FillHorizontalLineCommand(0, 'a'));
        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        assert manager.undo();
        for (int i = 1; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == ' ');
        }

        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');
    }

    private static void testCommandHistoryManager1() {
        Canvas canvas = new Canvas(10, 10);
        CommandHistoryManager manager = new CommandHistoryManager(canvas);

        assert manager.execute(new DrawPixelCommand(0, 0, '*'));
        assert manager.execute(new DrawPixelCommand(1, 2, '$'));
        assert manager.execute(new DrawPixelCommand(0, 1, '&'));
        assert manager.execute(new DrawPixelCommand(9, 9, 'a'));
        assert manager.execute(new DrawPixelCommand(7, 7, '~'));

        canvas.drawPixel(10, 10, 'X');
        canvas.drawPixel(-1, -1, 'X');
        canvas.drawPixel(11, 11, 'X');

        assert !manager.execute(new DrawPixelCommand(10, 10, 'X'));
        assert !manager.execute(new DrawPixelCommand(-1, -1, 'X'));
        assert !manager.execute(new DrawPixelCommand(11, 11, 'X'));

        assert (canvas.getPixel(0, 0) == '*');
        assert (canvas.getPixel(1, 2) == '$');
        assert (canvas.getPixel(0, 1) == '&');
        assert (canvas.getPixel(9, 9) == 'a');
        assert (canvas.getPixel(7, 7) == '~');

        assert manager.canUndo();
        assert !manager.canRedo();

        assert manager.undo();
        assert manager.canRedo();

        assert (canvas.getPixel(7, 7) == ' ');

        assert manager.redo();

        assert (canvas.getPixel(7, 7) == '~');

        assert manager.undo();
        assert manager.undo();
        assert manager.undo();
        assert manager.undo();
        assert manager.undo();
        assert !manager.undo();

        System.out.println(canvas.getDrawing());

        assert testClearCanvas(canvas);

        assert !manager.canUndo();

        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert !manager.redo();

        System.out.println(canvas.getDrawing());
    }

    private static boolean testClearCanvas(Canvas canvas) {
        for (int i = 0; i < canvas.getHeight(); ++i) {
            for (int j = 0; j < canvas.getWidth(); ++j) {
                if (canvas.getPixel(j, i) != ' ') {
                    return false;
                }
            }
        }

        return true;
    }
}
