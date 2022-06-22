package midterm;

public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.println("Animal " + this.name);
    }
}
