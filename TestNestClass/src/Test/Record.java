package Test;

public class Record {
    private static int createdCount = 0;
    private final byte[] rawData;
    private int magicNum = 77;

    public Record(byte[] rawData) {
        this.rawData = rawData;
        ++createdCount;
    }

    public static class Reader {
        private Record record;
        private int position = 0;

        public Reader(Record record) {
            this.record = record;
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

    private static void printCreatedCount() {
        System.out.printf("Created Count: %d\n", createdCount);
    }

    private void printValid() {
        System.out.println("It's valid");
    }
}
