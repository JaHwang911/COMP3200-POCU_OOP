package academy.pocu.comp2500.lab5;

import java.util.HashMap;

public class Gladiator extends Barbarian {
    private final int REST_ADD_HP = 10;
    private static final int MAX_MOVE_COUNT = 4;
    protected HashMap<String, Move> moves;

    public Gladiator(String name, int hp, int attack, int defence) {
        super(name, hp, attack, defence);

        this.moves = new HashMap<>(MAX_MOVE_COUNT);
    }

    public boolean addMove(Move move) {
        if (this.moves.size() >= MAX_MOVE_COUNT || this.moves.containsKey(move.getName())) {
            return false;
        }

        this.moves.put(move.getName(), move);

        return true;
    }

    public boolean removeMove(String moveName) {
        Move removedMove = this.moves.get(moveName);

        if (removedMove == null) {
            return false;
        }

        return this.moves.remove(moveName, removedMove);
    }

    public void attack(String moveName, Barbarian enemy) {
        if (!super.alive || this.equals(enemy)) {
            return;
        }

        Move move = this.moves.get(moveName);

        if (move == null || move.getPowerGauge() == 0) {
            System.out.println("Not enough move gage or not earned");
            return;
        }

        double damage = (super.attack / enemy.defence * move.getPower()) / 2.0;
        damage = damage < 1 ? 1 : damage;

        System.out.println("================");
        System.out.printf("%s attack(%s) -> %s%s", super.name, moveName, enemy.name, System.lineSeparator());
        enemy.takeDamage((int) damage);
        move.useMoves();
    }

    public void rest() {
        int requiredMaxHp = super.maxHp - super.hp;

        assert requiredMaxHp >= 0;

        if (requiredMaxHp < REST_ADD_HP) {
            super.hp = super.maxHp;
        } else {
            super.hp += REST_ADD_HP;
        }

        this.moves.forEach((key, value) -> {
            value.addPowerGauge();
        });
    }
}
