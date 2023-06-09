package academy.pocu.comp2500.lab8;

public class Schedule {
    private final int tick;
    private final int tickWhile;

    public Schedule(int tick, int tickWhile) {
        this.tick = tick;
        this.tickWhile = tickWhile;
    }

    public int getOnTickCount() {
        return this.tick;
    }

    public int getTickWhile() {
        return this.tickWhile;
    }

    public int getOffTickCount() {
        return this.tick + this.tickWhile;
    }
}
