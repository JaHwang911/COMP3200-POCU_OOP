package Test.app;

import Test.Record;

public class Program {
    public static void main(String[] args) {
        byte[] data = { 97, 98, 99, 100 };
        Record record = new Record(data);

        Record.Reader reader0 = new Record.Reader(record);

        assert reader0.canRead();

        reader0.readByte();
        reader0.readByte();
        reader0.readByte();
        reader0.readByte();

        assert !reader0.canRead();

        System.out.println("Nest class no prob");
    }
}