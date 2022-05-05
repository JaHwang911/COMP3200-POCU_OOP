package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String fullName;
    private ArrayList<Article> myArticles = new ArrayList<Article>(128);

    public User (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", firstName, lastName);
    }

    public String getUserName() {
        return this.fullName;
    }

    public Article writeArticle(String title, String content) {
        Article article = new Article(this.fullName, title, content);
        this.myArticles.add(article);

        return article;
    }
    public void readArticle(ArrayList<Article> articles) {
        for (Article a : articles) {
            System.out.println(a.getContent());
        }
    }

//    public Boolean modifiedArticle(Article article) {
//        String author = article.getAuthor();
//
//        if (!author.equals(userName)) {
//            return false;
//        }
//
//        return true;
//    }
}
