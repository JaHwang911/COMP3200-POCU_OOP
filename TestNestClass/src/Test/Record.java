package Test;

public class Record {
    protected static int createdCount = 0;

    protected final byte[] rawData;
    protected int magicNum = 77;
    private boolean hasReader;

    public Record(byte[] rawData) {
        this.rawData = rawData;
        ++createdCount;
    }

    public static class Reader {
        protected Record record;
        private int position = 0;

        public Reader(Record record) {
            this.record = record;
            this.record.hasReader = true;
        }

        public boolean canRead() {
            return this.position < this.record.rawData.length;
        }

        public void readByte() {
            System.out.printf("%c\n", this.record.rawData[position++]);
        }

        public void canAccessOuter() {
            this.record.printValid();
        }
    }

    public boolean isHasReader() {
        return this.hasReader;
    }

    private static void printCreatedCount() {
        System.out.printf("Created Count: %d\n", createdCount);
    }

    private void printValid() {
        System.out.println("It's valid");
    }
}
