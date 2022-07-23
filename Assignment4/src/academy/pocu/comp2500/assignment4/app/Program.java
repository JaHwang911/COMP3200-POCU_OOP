package academy.pocu.comp2500.assignment4.app;

import academy.pocu.comp2500.assignment4.Canvas;

import java.util.LinkedList;

public class Program {

    public static void main(String[] args) {
        LinkedList<Integer> nums = new LinkedList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);

        System.out.println(nums.getLast());

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
        assert (canvas.getPixel(9, 9) == 'a');

        assert !canvas.increasePixel(10, 10);
        assert !canvas.increasePixel(7, 7);
        assert !canvas.decreasePixel(8, 8);

        assert canvas.increasePixel(9, 9);
        assert (canvas.getPixel(9, 9) == 'b');

        assert canvas.decreasePixel(9, 9);
        assert (canvas.getPixel(9, 9) == 'a');

        canvas.fillHorizontalLine(0, '$');
        canvas.fillHorizontalLine(0, (char) 127);

        for (int i = 0; i < 10; ++i) {
            assert canvas.getPixel(i, 0) == '$';
        }

        canvas.fillVerticalLine(0, '*');
        canvas.fillVerticalLine(0, (char) 127);

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
        System.out.println(canvas.getDrawing());

        System.out.println("No prob: assignment4");
    }
}
