package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.Blog;
import academy.pocu.comp2500.assignment1.Post;
import academy.pocu.comp2500.assignment1.user.User;
import academy.pocu.comp2500.assignment1.user.UserType;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "kim", UserType.WRITER);

        blog.addPost(user1, "About CS", "CS is ...");
//        blog.addPost(user1, "About me", "I'm ...");
//        blog.addPost(user1, "About Event horizon", "Black hole is ...");
//        blog.addPost(user2, "About Unreal", "unreal is ...");
//        blog.addPost(user2, "About Unity", "Unity is ...");
        ArrayList<Post> allArticles = blog.getAllPosts();

        for (Post a : allArticles) {
            System.out.println(String.format("Title: %s", a.getTitle()));
            System.out.println(String.format("Content: %s", a.getBody()));
            System.out.println(String.format("author: %s", a.getAuthor()));
            System.out.println(String.format("Time: %s", a.getTime()));
            System.out.println("==============================");
        }

        Post firstArticle = allArticles.get(0);
        firstArticle.setTitle("About time");

        for (Post a : allArticles) {
            System.out.println(String.format("Title: %s", a.getTitle()));
            System.out.println(String.format("Content: %s", a.getBody()));
            System.out.println(String.format("author: %s", a.getAuthor()));
            System.out.println(String.format("Time: %s", a.getTime()));
            System.out.println("==============================");
        }
    }
}
