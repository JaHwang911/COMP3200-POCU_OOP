package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        testFilter();
        testFixPost();

        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob");
    }

    private static void testFilter() {
        Blog blog = new Blog();
        User a1 = new User("a1", UserType.WRITER);
        User a2 = new User("a2", UserType.WRITER);
        Post post1 = new Post(a1, "p1", "body");
        Post post2 = new Post(a1, "p2", "body");
        Post post3 = new Post(a2, "p3", "body");
        Post post4 = new Post(a2, "p4", "body");

        post1.addTag(a1, "t1");
        post2.addTag(a1, "t2");
        post3.addTag(a2, "t1");
        post4.addTag(a2, "t2");

        blog.addPost(post1);
        blog.addPost(post2);
        blog.addPost(post3);
        blog.addPost(post4);

        blog.setFilterByTag("t1");
        blog.setSorted(OrderType.CREATED_DESC);

        var filteredTag = blog.getPosts();

        assert filteredTag.size() == 2;
        assert filteredTag.get(0).getTitle().equals("p1");
        assert filteredTag.get(1).getTitle().equals("p3");

        blog.setFilterByTag(null);
        blog.setFilterByAuthor(a1);

        var filteredUser = blog.getPosts();

        assert filteredUser.size() == 2;
        assert filteredUser.get(0).getTitle().equals("p1");
        assert filteredUser.get(1).getTitle().equals("p2");

        blog.setFilterByAuthor(null);
        blog.setFilterByTag("t1");
        blog.setFilterByAuthor(a2);

        var filteredCombo = blog.getPosts();

        assert filteredCombo.size() == 1;
        assert filteredCombo.get(0).getTitle().equals("p3");
    }

    private static void testFixPost() {
        Blog blog = new Blog();
        User n1 = new User("n1", UserType.WRITER);
        User n2 = new User("n1", UserType.WRITER);

        Post p1 = new Post(n1, "t1", "body");
        assert !p1.modifyTitle(n2, "Hello");
    }
}
