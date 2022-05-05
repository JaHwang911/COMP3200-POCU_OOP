package academy.pocu.comp2500.assignment1;

public class User {
    private String userName;

    public void writeArticle() {

    }

    public Boolean modifiedArticle(Article article) {
        String author = article.getAuthor();

        if (!author.equals(userName)) {
            return false;
        }

        return true;
    }
}
