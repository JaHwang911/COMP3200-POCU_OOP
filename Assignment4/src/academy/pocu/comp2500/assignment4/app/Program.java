package academy.pocu.comp2500.assignment4.app;

import academy.pocu.comp2500.assignment4.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Program {

    public static void main(String[] args) {
//        testCanvas();
//        testOverdrawAnalyzer();
//        testCommandHistoryManagerCanvas0();
//        testCommandHistoryManagerOverdrawAnalyzer0();
        testOfficial0();

        System.out.println("No prob: assignment4");
    }

    private static void testOfficial0() {
        OverdrawAnalyzer canvas = new OverdrawAnalyzer(25, 30);
        CommandHistoryManager manager = new CommandHistoryManager(canvas);

        manager.execute(new FillVerticalLineCommand(1, '3'));
        manager.execute(new ToUpperCommand(3, 0));
        manager.execute(new ClearCommand());
        manager.execute(new FillHorizontalLineCommand(0, 'J'));
        manager.execute(new ToLowerCommand(3, 2));
        manager.execute(new ClearCommand());
        manager.execute(new FillHorizontalLineCommand(1, 'G'));
        manager.undo();
        manager.undo();
        manager.execute(new DecreasePixelCommand(2, 3));
        manager.execute(new FillHorizontalLineCommand(4, 'V'));
        manager.redo();
        manager.execute(new ToLowerCommand(1, 0));
        manager.redo();
        manager.execute(new FillHorizontalLineCommand(4, 't'));
        manager.execute(new IncreasePixelCommand(3, 2));
        manager.execute(new DrawPixelCommand(3, 0, '_'));
        manager.execute(new ClearCommand());
        manager.redo();
        manager.execute(new IncreasePixelCommand(1, 2));

        var real = canvas.getPixelHistory(0, 2);
        assert (real.size() == 0);
    }

    private static void testOfficial1() {
        Canvas canvas = new Canvas(25, 30);
        CommandHistoryManager manager = new CommandHistoryManager(canvas);

        manager.execute(new FillVerticalLineCommand(21, '8'));
        manager.execute(new FillHorizontalLineCommand(10, '#'));
        manager.execute(new ToLowerCommand(5, 1));
        manager.redo();
        manager.undo();

        System.out.println(canvas.getDrawing());
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
        assert isClearCanvas(canvas);

        System.out.println(canvas.getDrawing());
    }

    private static void testOverdrawAnalyzer() {
        OverdrawAnalyzer canvas = new OverdrawAnalyzer(10, 10);
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
        assert isClearCanvas(canvas);

        System.out.println(canvas.getDrawing());
    }

    private static void testCommandHistoryManagerCanvas0() {
        Canvas canvas = new Canvas(10, 10);
        CommandHistoryManager manager = new CommandHistoryManager(canvas);
        ArrayList<ICommand> commands = new ArrayList<>();

        commands.add(new DrawPixelCommand(0, 0, 'a'));
        commands.add(new IncreasePixelCommand(0, 0));
        commands.add(new DecreasePixelCommand(0, 0));
        commands.add(new ToUpperCommand(0, 0));
        commands.add(new ToLowerCommand(0, 0));
        commands.add(new FillHorizontalLineCommand(0, 'a'));
        commands.add(new FillVerticalLineCommand(0, 'a'));
        commands.add(new ClearCommand());

        assert !manager.canUndo();
        assert !manager.canRedo();

        assert manager.execute(commands.get(0));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == ' ');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        assert manager.execute(commands.get(1));
        assert (canvas.getPixel(0, 0) == 'b');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'b');

        assert manager.execute(commands.get(2));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'b');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        assert manager.execute(commands.get(3));
        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'A');

        assert manager.execute(commands.get(4));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        // FillHorizontalLine
        assert manager.execute(commands.get(5));
        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        for (int i = 1; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == ' ');
        }

        assert manager.redo();
        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        // FillVerticalLine
        assert manager.execute(commands.get(6));
        for (int i = 0; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == 'a');
        }

        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        for (int i = 1; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == ' ');
        }

        assert manager.redo();
        for (int i = 0; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == 'a');
        }

        // Clear
        assert manager.execute(commands.get(7));
        for (int i = 0; i < canvas.getHeight(); ++i) {
            for (int j = 0; j < canvas.getWidth(); ++j) {
                assert (canvas.getPixel(j, i) == ' ');
            }
        }

        manager.undo();
        for (int i = 0; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == 'a');
        }

        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        manager.redo();
        for (int i = 0; i < canvas.getHeight(); ++i) {
            for (int j = 0; j < canvas.getWidth(); ++j) {
                assert (canvas.getPixel(j, i) == ' ');
            }
        }

        for (int i = 0; i < 8; ++i) {
            assert !manager.execute(commands.get(i));
        }
    }

    private static void testCommandHistoryManagerOverdrawAnalyzer0() {
        OverdrawAnalyzer canvas = new OverdrawAnalyzer(10, 10);
        CommandHistoryManager manager = new CommandHistoryManager(canvas);
        ArrayList<ICommand> commands = new ArrayList<>();

        commands.add(new DrawPixelCommand(0, 0, 'a'));
        commands.add(new IncreasePixelCommand(0, 0));
        commands.add(new DecreasePixelCommand(0, 0));
        commands.add(new ToUpperCommand(0, 0));
        commands.add(new ToLowerCommand(0, 0));
        commands.add(new FillHorizontalLineCommand(0, 'a'));
        commands.add(new FillVerticalLineCommand(0, 'a'));
        commands.add(new ClearCommand());

        assert !manager.canUndo();
        assert !manager.canRedo();

        assert manager.execute(commands.get(0));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == ' ');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        LinkedList<Character> real = canvas.getPixelHistory(0, 0);
        LinkedList<Character> expected = new LinkedList<>();
        expected.add('a');
        expected.add(' ');
        expected.add('a');

        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        assert manager.execute(commands.get(1));
        assert (canvas.getPixel(0, 0) == 'b');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'b');

        expected.add('b');
        expected.add('a');
        expected.add('b');
        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        assert manager.execute(commands.get(2));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'b');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        expected.add('a');
        expected.add('b');
        expected.add('a');
        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        assert manager.execute(commands.get(3));
        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'A');

        expected.add('A');
        expected.add('a');
        expected.add('A');
        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        assert manager.execute(commands.get(4));
        assert (canvas.getPixel(0, 0) == 'a');
        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'A');
        assert manager.redo();
        assert (canvas.getPixel(0, 0) == 'a');

        expected.add('a');
        expected.add('A');
        expected.add('a');
        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        // FillHorizontalLine
        assert manager.execute(commands.get(5));
        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        for (int i = 1; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == ' ');
        }

        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        assert manager.redo();
        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        real = canvas.getPixelHistory(0, 0);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }

        // FillVerticalLine
        assert manager.execute(commands.get(6));
        for (int i = 0; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == 'a');
        }

        assert manager.undo();
        assert (canvas.getPixel(0, 0) == 'a');
        for (int i = 1; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == ' ');
        }

        assert manager.redo();
        for (int i = 0; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == 'a');
        }

        // Clear
        assert manager.execute(commands.get(7));
        for (int i = 0; i < canvas.getHeight(); ++i) {
            for (int j = 0; j < canvas.getWidth(); ++j) {
                assert (canvas.getPixel(j, i) == ' ');
            }
        }

        manager.undo();
        for (int i = 0; i < canvas.getHeight(); ++i) {
            assert (canvas.getPixel(0, i) == 'a');
        }

        for (int i = 0; i < canvas.getWidth(); ++i) {
            assert (canvas.getPixel(i, 0) == 'a');
        }

        manager.redo();
        for (int i = 0; i < canvas.getHeight(); ++i) {
            for (int j = 0; j < canvas.getWidth(); ++j) {
                assert (canvas.getPixel(j, i) == ' ');
            }
        }

        for (int i = 0; i < 8; ++i) {
            assert !manager.execute(commands.get(i));
        }
    }

    private static void testCommandHistoryManagerCanvas1() {
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

        assert isClearCanvas(canvas);

        assert !manager.canUndo();

        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert !manager.redo();

        assert (canvas.getPixel(0, 0) == '*');
        assert (canvas.getPixel(1, 2) == '$');
        assert (canvas.getPixel(0, 1) == '&');
        assert (canvas.getPixel(9, 9) == 'a');
        assert (canvas.getPixel(7, 7) == '~');

        assert manager.undo();
        assert manager.undo();
        assert manager.execute(new ClearCommand());
        assert isClearCanvas(canvas);
        assert !manager.canRedo();
        assert manager.undo();

        assert (canvas.getPixel(0, 0) == '*');
        assert (canvas.getPixel(1, 2) == '$');
        assert (canvas.getPixel(0, 1) == '&');
        assert (canvas.getPixel(9, 9) == ' ');
        assert (canvas.getPixel(7, 7) == ' ');
    }

    private static void testCommandHistoryManagerOverdrawAnalyzer1() {
        OverdrawAnalyzer canvas = new OverdrawAnalyzer(10, 10);
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

        assert isClearCanvas(canvas);

        assert !manager.canUndo();

        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert manager.redo();
        assert !manager.redo();

        assert (canvas.getPixel(0, 0) == '*');
        assert (canvas.getPixel(1, 2) == '$');
        assert (canvas.getPixel(0, 1) == '&');
        assert (canvas.getPixel(9, 9) == 'a');
        assert (canvas.getPixel(7, 7) == '~');

        assert manager.undo();
        assert manager.undo();
        assert manager.execute(new ClearCommand());
        assert isClearCanvas(canvas);
        assert !manager.canRedo();
        assert manager.undo();

        assert (canvas.getPixel(0, 0) == '*');
        assert (canvas.getPixel(1, 2) == '$');
        assert (canvas.getPixel(0, 1) == '&');
        assert (canvas.getPixel(9, 9) == ' ');
        assert (canvas.getPixel(7, 7) == ' ');

        LinkedList<Character> expected = new LinkedList<>();
        expected.add('~');
        expected.add(' ');
        expected.add('~');
        expected.add(' ');
        expected.add('~');
        expected.add(' ');

        var real = canvas.getPixelHistory(7, 7);

        assert (real.size() == expected.size());
        for (int i = 0; i < real.size(); ++i) {
            assert (real.get(i) == expected.get(i));
        }
    }

    private static boolean isClearCanvas(Canvas canvas) {
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
