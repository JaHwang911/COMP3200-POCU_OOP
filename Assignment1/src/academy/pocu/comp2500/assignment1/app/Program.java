package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.Blog;
import academy.pocu.comp2500.assignment1.Article;
import academy.pocu.comp2500.assignment1.User;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang");
        User user2 = new User("Baro", "kim");

        blog.addArticle(user1.writeArticle("About CS", "CS is ..."));
        blog.addArticle(user1.writeArticle("About Me", "my name is ..."));
        blog.addArticle(user1.writeArticle("About Event horizon", "Black hole is ..."));
        blog.addArticle(user2.writeArticle("About Unreal engine", "What the fuck is that"));
        blog.addArticle(user2.writeArticle("About Unity", "Unity is ..."));
        ArrayList<Article> allArticles = blog.getAllArticle();

        for (Article a : allArticles) {
            System.out.println(String.format("Title: %s", a.getTitle()));
            System.out.println(String.format("Content: %s", a.getContent()));
            System.out.println(String.format("author: %s", a.getAuthor()));
            System.out.println("==============================");
        }
    }
}
