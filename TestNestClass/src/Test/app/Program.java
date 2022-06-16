package Test.app;

import Test.Record;

public class Program {
    public static void main(String[] args) {
        byte[] data = { 97, 98, 99, 100 };
        Record record = new Record(data);

        Record.Reader reader = new Record.Reader(record);
        reader.accessOuter();

        assert reader.canRead();
        reader.readByte();
        reader.readByte();
        reader.readByte();

        assert reader.canRead();
        reader.readByte();
        assert !reader.canRead();

        System.out.println("Nest class no prob");
    }
}