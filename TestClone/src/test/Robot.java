package test;

public final class Robot implements Cloneable {
    private Head head;
    private final String name;
    private int intelligence;

    public Robot(Head head, String name, int intelligence) {
        this.head = head;
        this.name = name;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
