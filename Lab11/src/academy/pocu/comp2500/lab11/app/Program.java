package academy.pocu.comp2500.lab11.app;

import academy.pocu.comp2500.lab11.App;
import java.io.*;

public class Program {

    public static void main(String[] args) {
        App app = new App();

        app.run(new BufferedReader(new InputStreamReader(System.in)), System.out, System.err);
        System.out.println("No prob: lab 11");
    }
}
