package Test;

import java.io.Reader;

public class Record2 extends Record {
    private int num = 1;

    public Record2(byte[] rawData) {
        super(rawData);
    }

    public void printRead() {
        Reader reader = new Reader();
        reader.readByte();
    }
}
