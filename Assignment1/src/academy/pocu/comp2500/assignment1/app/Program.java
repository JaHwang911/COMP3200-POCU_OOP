package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {

        ArrayList<String> str1 = new ArrayList<>();
        ArrayList<String> str2 = new ArrayList<>();

        str1.add("1");
        str1.add("2");

        str2.add("1");
        str2.add("2");
        str2.add("3");

        System.out.println(str1.contains(str2));
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

        post1.addTag("a1", "t1");
        post1.addTag("a1", "Computer");
        post2.addTag("a1", "t2");
        post3.addTag("a2", "t1");
        post4.addTag("a2", "t2");

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

        tags.clear();
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor("a1");

        var filteredUser = blog.getPosts();

        assert filteredUser.size() == 2;
        assert filteredUser.get(0).getTitle().equals("p1");
        assert filteredUser.get(1).getTitle().equals("p2");

        tags.add("t1");
        blog.setFilterOnOffByAuthor(null);
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor("a2");

        var filteredCombo = blog.getPosts();

        assert filteredCombo.size() == 1;
        assert filteredCombo.get(0).getTitle().equals("p3");

        tags.clear();
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor(null);

        var notSetFilterPosts = blog.getPosts();

        assert notSetFilterPosts.size() == 4;

        tags.clear();
        tags.add("t1");
        tags.add("Computer");

        blog.setFilterOnOffByTags(tags);
        var multiSetTags = blog.getPosts();

        assert multiSetTags.size() == 1;
    }
}
