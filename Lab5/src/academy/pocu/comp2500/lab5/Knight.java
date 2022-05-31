package academy.pocu.comp2500.lab5;

public class Knight extends Gladiator {
    protected Pet pet;

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
        enemy.takeDamage((int) damage);
    }

    public void setPet(Pet petOrNull) {
        this.pet = petOrNull;
    }
}
