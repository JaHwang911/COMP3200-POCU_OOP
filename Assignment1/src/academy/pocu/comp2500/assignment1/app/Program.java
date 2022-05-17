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
        Post post1 = new Post("a1", "p1", "body");
        Post post2 = new Post("a1", "p2", "body");
        Post post3 = new Post("a2", "p3", "body");
        Post post4 = new Post("a2", "p4", "body");

        ArrayList<String> tag1 = new ArrayList<>(2);
        tag1.add("t1");
        tag1.add("Computer");

        ArrayList<String> tag2 = new ArrayList<>(2);
        tag2.add("t2");
        tag2.add("Game");

        post1.addTags("a1", tag1);
        post2.addTags("a1", tag2);
        post3.addTags("a2", tag1);
        post4.addTags("a2", tag2);

        blog.addPost(post1);
        blog.addPost(post2);
        blog.addPost(post3);
        blog.addPost(post4);

        ArrayList<String> tags = new ArrayList<>(2);
        tags.add("t1");

        blog.setFilterOnOffByTags(tags);
        blog.setOrderType(OrderType.CREATED_DESC);

        var filteredTag = blog.getPosts();

        assert filteredTag.size() == 2;
        assert filteredTag.get(0).getTitle().equals("p1");
        assert filteredTag.get(1).getTitle().equals("p3");

        blog.setFilterOnOffByTags(null);
        blog.setFilterOnOffByAuthor("a1");

        var filteredUser = blog.getPosts();

        assert filteredUser.size() == 2;
        assert filteredUser.get(0).getTitle().equals("p1");
        assert filteredUser.get(1).getTitle().equals("p2");

        blog.setFilterOnOffByAuthor(null);
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor("a2");

        var filteredCombo = blog.getPosts();

        assert filteredCombo.size() == 1;
        assert filteredCombo.get(0).getTitle().equals("p3");

        blog.setFilterOnOffByTags(null);
        blog.setFilterOnOffByAuthor(null);

        var notSetFilterPosts = blog.getPosts();

        assert notSetFilterPosts.size() == 4;

//        blog.setFilterOnOffByTag(null);
//        blog.setFilterOnOffByAuthor(null);
//
//        blog.setFilterOnOffByTag("t1");
//        blog.setFilterOnOffByAuthor("a1");
//        blog.setFilterOnOffByTag("Computer");
//
//        var pingPongFilterPosts = blog.getPosts();
    }
}
