package academy.pocu.comp2500.lab5;

import academy.pocu.comp2500.lab5.Barbarian;

import java.util.ArrayList;

public class Users {
    private final ArrayList<Barbarian> users;

    public Users() {
        this.users = new ArrayList<>();
    }

    public void addUser(Barbarian user) {
        this.users.add(user);
    }

    public void printUsers() {
        for (Barbarian user : this.users) {
            System.out.println("====================");
            System.out.printf("Name: %s%s", user.name, System.lineSeparator());
            System.out.printf("Max HP: %s%s", user.maxHp, System.lineSeparator());
            System.out.printf("HP: %s%s", user.hp, System.lineSeparator());
            System.out.printf("Attack: %s%s", user.attack, System.lineSeparator());
            System.out.printf("defence: %s%s", user.defence, System.lineSeparator());
            System.out.println("====================");
        }
    }
}
