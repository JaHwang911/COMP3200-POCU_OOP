package test.app;

import test.Record;
import test.InheritanceRecord;
import test.NonStaticRecord;

public class Program {
    public static void main(String[] args) {
        byte[] data = { 97, 98, 99, 100 };

        testRecord(data);
        testInheritanceNest(data);
        testNonStaticNested(data);

        System.out.println("Nest class no prob");
    }

    private static void testRecord(byte[] data) {
        Record record = new Record(data);
        assert record.readerCount() == 0;
        Record.Reader reader0 = new Record.Reader(record);
        assert record.readerCount() == 1;

        testReader(reader0);
    }

    private static void testInheritanceNest(byte[] data) {
        Record record = new Record(data);

        InheritanceRecord reader0 = new InheritanceRecord(record);

        testReader(reader0);
    }

    private static void testReader(Record.Reader reader) {
        assert reader.canRead();

        reader.readByte();
        reader.readByte();
        reader.readByte();
        reader.readByte();

        assert !reader.canRead();
    }

    private static void testNonStaticNested(byte[] data) {
        NonStaticRecord.Reader reader = new NonStaticRecord(data).new Reader();
        System.out.println(reader.getClass().getName());
    }
}