package Test.app;

import Test.Record;

public class Program {
    public static void main(String[] args) {
        byte[] data = { 97, 98, 99, 100 };
        Record record = new Record(data);

        record.readRead();

        System.out.println("Nest class no prob");
    }
}