package Test;

public class Record {
    private static int createdCount = 0;
    private final byte[] rawData;
    private int magicNum = 77;
    private int testNum = 1;

    public Record(byte[] rawData) {
        this.rawData = rawData;
        ++createdCount;
    }

    public class Reader {
        private int position = 0;

        public boolean canRead() {
            return this.position < rawData.length;
        }

        public void readByte() {
            System.out.printf("%c\n", rawData[position++]);
        }
    }

    private static void printCreatedCount() {
        System.out.printf("Created Count: %d\n", createdCount);
    }

    public void readRead() {
        Reader reader= new Reader();
        reader.readByte();
    }

    private void printValid() {
        System.out.println("It's valid");
    }
}
