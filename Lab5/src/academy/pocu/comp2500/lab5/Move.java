package academy.pocu.comp2500.lab5;

public class Move {
    private String name;
    private final int power;
    private int count;

    public Move(String name, int power, int count) {
        this.name = name;
        this.power = power;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return this.power;
    }

    public int getCount() {
        return this.count;
    }

    public void useMoves() {
        --this.count;
    }

    public void addMoveCount() {
        ++this.count;
    }
}
