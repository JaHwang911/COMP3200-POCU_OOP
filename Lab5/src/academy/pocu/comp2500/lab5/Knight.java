package academy.pocu.comp2500.lab5;

public class Knight extends Gladiator {
    private Pet pet;

    public Knight(String name, int hp, int attack, int defence) {
        super(name, hp, attack, defence);
    }

    public void attackTogether(Barbarian enemy) {
        if (this.pet == null || !super.alive || this.equals(enemy)) {
            return;
        }

        double damage = (super.attack + this.pet.getAttack() - enemy.defence) / 2.0;
        damage = damage < 1 ? 1 : damage;

        System.out.println("================");
        System.out.printf("%s attack together -> %s%s", super.name, enemy.name, System.lineSeparator());
        System.out.println("================");
        System.out.printf("%s attack -> %s%s", this.name, enemy.name, System.lineSeparator());

        if ((int) damage >= enemy.hp) {
            enemy.hp = 0;
            enemy.alive = false;
            System.out.println("You died");
            System.out.println("================");
            return;
        }

        int beforeHp = enemy.hp;
        enemy.hp -= (int) damage;

        System.out.printf("%s: %s", enemy.name, System.lineSeparator());
        System.out.printf("Take %d damages%s", (int) damage, System.lineSeparator());
        System.out.printf("HP: %d -> %d%s", beforeHp, enemy.hp, System.lineSeparator());
        System.out.println("================");
    }

    public void setPet(Pet petOrNull) {
        this.pet = petOrNull;
    }
}
