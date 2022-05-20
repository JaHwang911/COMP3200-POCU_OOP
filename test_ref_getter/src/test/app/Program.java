package test.app;

import test.*;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("n1");
        DangerGetter d = new DangerGetter(user1);

        User user2 = d.getUser();
        user2.setName("fucker");

        System.out.println(user1.getUserName());

        System.out.println("No prob");
    }
}
