package academy.pocu.comp2500.lab5;

public class Move {
    private final String name;
    private final int power;
    private int powerGauge;
    private final int maxPowerGauge;

    public Move(String name, int power, int powerGauge) {
        this.name = name;
        this.power = power;
        this.maxPowerGauge = powerGauge;
        this.powerGauge = this.maxPowerGauge;
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return this.power;
    }

    public int getPowerGauge() {
        return this.powerGauge;
    }

    public void useMoves() {
        assert this.powerGauge != 0 : "Wrong attack condition";

        --this.powerGauge;
    }

    public void addPowerGauge() {
        if (this.maxPowerGauge == this.powerGauge) {
            return;
        }

        ++this.powerGauge;
    }
}
