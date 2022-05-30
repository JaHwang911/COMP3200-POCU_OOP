package academy.pocu.comp2500.lab5;

public class Barbarian {
    protected String name;
    protected int hp;
    protected final int attack;
    protected final int defence;
    protected boolean alive;

    public Barbarian(String name, int hp, int attack, int defence) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.alive = true;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void attack(Barbarian enemy) {
        double damage = (this.attack - enemy.defence) / 2.0;
        damage = damage < 0 ? 1 : damage;

        enemy.takeDamage((int)damage);
    }

    protected void takeDamage(int damage) {
        if (damage >= this.hp) {
            this.hp = 0;
            this.alive = false;
            return;
        }

        this.hp -= damage;
    }
}
