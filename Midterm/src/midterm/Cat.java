package midterm;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public void sayCatName() {
        System.out.println("Cat " + this.name);
    }

    public void introduce() {
        System.out.println("Hi! I'm a cat!");
    }
}
