package academy.pocu.comp2500.lab5;

import java.util.HashMap;

public class Gladiator extends Barbarian {
    private static final int MAX_MOVE_COUNT = 4;
    private HashMap<String, Move> moves;

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

    public boolean removeMove(Move move) {
        return this.moves.remove(move.getName(), move);
    }

    public void attack(String moveName, Barbarian enemy) {
        Move move = this.moves.get(moveName);

        if (move == null || move.getCount() == 0) {
            return;
        }

        double damage = ((double)this.attack / enemy.defence * move.getPower()) / 2.0;
        damage = damage < 0 ? 1 : damage;

        enemy.takeDamage((int)damage);
        move.useMoves();
    }

    protected void rest() {
        this.hp += 10;

        this.moves.forEach((key, value) -> {
            value.addMoveCount();
        });
    }
}
