package sample;

public class DigitalClock extends Clock {
    public boolean isBeforeMidday() {
        return (super.seconds / (DAY_IN_SECONDS / 2) == 0);
    }

    public void setIsBeforeMidday(boolean isAm) {

    }

    public void setHours(byte hours) {
        int value = 60 * 60 * hours;
        while (value < 0) {
            value += DAY_IN_SECONDS;
        }

        super.seconds = value % DAY_IN_SECONDS;
    }

    public void setMinutes(byte minutes) {

    }
}
