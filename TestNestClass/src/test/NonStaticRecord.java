package test;

public class NonStaticRecord {
    private byte[] rawData;

    public NonStaticRecord(byte[] rawData) {
        this.rawData = rawData;
    }

    public class Reader {
        private int position;

        public boolean canRead() {
            return this.position < rawData.length;
        }

        public void readData() {
            System.out.println(rawData[position++]);
        }
    }
}
