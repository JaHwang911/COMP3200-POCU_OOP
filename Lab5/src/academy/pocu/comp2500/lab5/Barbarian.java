package academy.pocu.comp2500.lab5;

public class Barbarian {
    protected String name;
    protected final int maxHp;
    protected int hp;
    protected final int attack;
    protected final int defence;
    protected boolean alive;

    public Barbarian(String name, int hp, int attack, int defence) {
        this.name = name;
        this.maxHp = hp;
        this.hp = this.maxHp;
        this.attack = attack;
        this.defence = defence;
        this.alive = true;
    }

    public int getHp() {
        return this.hp;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void attack(Barbarian enemy) {
        if (!this.alive || this.equals(enemy)) {
            return;
        }

        double damage = (this.attack - enemy.defence) / 2.0;
        damage = damage < 0 ? 1 : damage;

        System.out.println("================");
        System.out.printf("%s attack -> %s%s", this.name, enemy.name, System.lineSeparator());
        enemy.takeDamage((int) damage);
    }

    protected void takeDamage(int damage) {
        assert damage > 0;

        if (damage >= this.hp) {
            this.hp = 0;
            this.alive = false;
            System.out.println("You died");
            System.out.println("================");
            return;
        }

        int beforeHp = this.hp;
        this.hp -= damage;
        System.out.printf("%s: %s", this.name, System.lineSeparator());
        System.out.printf("Take %d damages%s", damage, System.lineSeparator());
        System.out.printf("HP: %d -> %d%s", beforeHp, this.hp, System.lineSeparator());
        System.out.println("================");
    }
}
