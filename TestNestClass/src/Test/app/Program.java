package Test.app;

import Test.InheritanceRecord;
import Test.Record;

public class Program {
    public static void main(String[] args) {
        byte[] data = { 97, 98, 99, 100 };

        testRecord(data);
        testInheritanceNest(data);

        System.out.println("Nest class no prob");
    }

    private static void testRecord(byte[] data) {
        Record record = new Record(data);
        assert !record.isHasReader();
        Record.Reader reader0 = new Record.Reader(record);
        assert record.isHasReader();

        testReader(reader0);
    }

    private static void testInheritanceNest(byte[] data) {
        InheritanceRecord inheritanceRecord = new InheritanceRecord(data);
        InheritanceRecord.Reader reader0 = new Record.Reader(inheritanceRecord);

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
}