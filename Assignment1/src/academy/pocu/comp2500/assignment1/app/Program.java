package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.Blog;
import academy.pocu.comp2500.assignment1.Post;
import academy.pocu.comp2500.assignment1.user.User;
import academy.pocu.comp2500.assignment1.user.UserType;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        testAddPost();
        testGetByTag();
        testGetByAuthor();

        System.out.println("No prob");
    }

    private static void testAddPost() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "computer");

        Post post2 = new Post(user1, "About me", "I'm ...");
        post2.addTag(user1, "i my me mine");

        Post post3 = new Post(user1, "About event horizon", "Black hole is ...");
        post3.addTag(user1, "Space");

        blog.addPost(post1);
        blog.addPost(post2);
        blog.addPost(post3);

        ArrayList<Post> posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About CS");
        assert posts.get(1).getTitle().equals("About me");
        assert posts.get(2).getTitle().equals("About event horizon");
    }

    private static void testGetByTag() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "Computer");

        Post post2 = new Post(user1, "About event horizon", "Black hole is ...");
        post2.addTag(user1, "Space");

        blog.addPost(post1);
        blog.addPost(post2);

        ArrayList<Post> posts = blog.getPostsByTagOrNUll("Space");

        assert posts.size() == 1;
        assert posts.get(0).getTitle().equals("About event horizon");

        posts = blog.getPostsByTagOrNUll("Android");

        assert posts.size() == 0;
    }

    private static void testGetByAuthor() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "Computer");

        Post post2 = new Post(user2, "About Unreal", "Unreal is ...");
        post2.addTag(user1, "Game");

        blog.addPost(post1);
        blog.addPost(post2);

        ArrayList<Post> posts = blog.getPostsByAuthorOrNull("Baro Kim");

        assert posts.size() == 1;
        assert posts.get(0).getTitle().equals("About Unreal");
        assert posts.get(0).getAuthor().equals("Baro Kim");

        posts = blog.getPostsByTagOrNUll("Junseok Kim");

        assert posts.size() == 0;
    }
}
