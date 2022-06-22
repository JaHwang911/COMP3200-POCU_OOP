package sample;

public class SevenSegmentDisplay {
    public enum Segment {
        A,
        B,
        C,
        D,
        E,
        F,
        G
    }

    private boolean isOn;
    private byte digit;

    public SevenSegmentDisplay(byte digit) {
        this.digit = digit;
    }

    public void set(Segment segment, boolean on) {

    }
}
