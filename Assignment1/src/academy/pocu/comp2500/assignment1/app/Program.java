package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        testFilter();

        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob");
    }

    private static void testFilter() {
        Blog blog = new Blog();
        User a1 = new User("a1", UserType.WRITER);
        User a2 = new User("a2", UserType.WRITER);
        blog.addPost(new Post(a1, "p1", "body"));
        blog.addPost(new Post(a1, "p2", "body"));
        blog.addPost(new Post(a2, "p3", "body"));
        blog.addPost(new Post(a2, "p4", "body"));

        var posts = blog.getPosts();

        posts.get(0).addTag(a1, "t1");
        posts.get(1).addTag(a1, "t2");
        posts.get(2).addTag(a2, "t1");
        posts.get(3).addTag(a2, "t2");

        blog.setFilterByTag(true, "t1");

        var filteredTag = blog.getPosts();

        assert filteredTag.size() == 2;
        assert filteredTag.get(0).getTitle().equals("p1");
        assert filteredTag.get(1).getTitle().equals("p3");

        blog.setFilterByTag(false, null);
        blog.setFilterByAuthor(true, a1);

        var filteredUser = blog.getPosts();

        assert filteredUser.size() == 2;
        assert filteredUser.get(0).getTitle().equals("p1");
        assert filteredUser.get(1).getTitle().equals("p2");

        blog.setFilterByAuthor(false, null);
        blog.setFilterByTag(true, "t1");
        blog.setFilterByAuthor(true, a2);

        var filteredCombo = blog.getPosts();

        assert filteredCombo.size() == 1;
        assert filteredCombo.get(0).getTitle().equals("p3");
    }
}
