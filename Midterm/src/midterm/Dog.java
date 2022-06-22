package midterm;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void sayDogName() {
        System.out.println("Dog " + this.name);
    }

    public void introduce() {
        System.out.println("Hi! I'm dog!");
    }
}
