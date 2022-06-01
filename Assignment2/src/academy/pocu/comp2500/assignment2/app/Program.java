package academy.pocu.comp2500.assignment2.app;

import academy.pocu.comp2500.assignment2.Color;

public class Program {

    public static void main(String[] args) {
	    Color color = new Color(0x00, 0x80, 0x00);
        int color0 = color.getColor();
        System.out.printf("0x%06x%s", color0, System.lineSeparator());
    }
}
