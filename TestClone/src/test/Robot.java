package test;

public final class Robot implements Cloneable {
    private Head head;
    private final String name;
    private int hp;
    private int intelligence;

    public Robot(Head head, String name, int hp, int intelligence) {
        this.head = head;
        this.name = name;
        this.hp = hp;
        this.intelligence = intelligence;
    }

    public Head getHead() {
        return head;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return this.hp;
    }

    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Robot cloneRobot;

        try {
            cloneRobot = (Robot) super.clone();
            cloneRobot.head = (Head) this.head.clone();
        } catch (CloneNotSupportedException e) {
            throw e;
        }

        return cloneRobot;
    }
}
