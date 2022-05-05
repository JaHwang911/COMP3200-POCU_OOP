package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Blog {
    private final ArrayList<Article> articles = new ArrayList<Article>(128);

    public void addArticle(Article article) {
        articles.add(article);
    }

    public ArrayList<Article> getAllArticle() {
        return articles;
    }

    public ArrayList<Article> getArticlesOrNullByAuthor(String authorName) {
        ArrayList<Article> resultArticles = new ArrayList<Article>();

        for (Article a : this.articles) {
            if (a.getAuthor().equals(authorName)) {
                resultArticles.add(a);
            }
        }

        return resultArticles;
    }
}
