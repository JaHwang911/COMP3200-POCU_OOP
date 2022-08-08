package test;

public class InheritanceRecord extends Record.Reader {
    private int magicNum = 88;

    public InheritanceRecord(Record record) {
        super(record);
    }
}
