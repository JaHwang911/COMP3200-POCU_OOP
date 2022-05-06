package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.user.User;
import academy.pocu.comp2500.assignment1.user.UserType;

import java.util.ArrayList;
//정렬 방법
//작성 일시 내림차순
//작성 일시 오름차순
//수정 일시 내림차순
//수정 일시 오름차순
//제목(사전 순서) 오름차순
public class Blog {
    private ArrayList<Post> posts;

    public Blog() {
         this.posts = new ArrayList<>(128);
    }

    public ArrayList<Post> getPostsByAuthorOrNull(String authorName) {
        ArrayList<Post> resultPosts = new ArrayList<>(posts.size());

        for (Post a : this.posts) {
            if (a.getAuthor().equals(authorName)) {
                resultPosts.add(a);
            }
        }

        return resultPosts;
    }

    public ArrayList<Post> getPostsByTagOrNUll(String tag) {
        ArrayList<Post> resultPosts = new ArrayList<Post>(posts.size());

        for (Post a : posts) {
            if (a.getTag().equals(tag)) {
                resultPosts.add(a);
            }
        }

        return resultPosts;
    }

    public void addPost(User user, String title, String text) {
        if (user.getUserType() == UserType.VISITOR) {
            System.out.println("Invalid user type...");
            return;
        }

        Post article = new Post(user.getUserName(), title, text);
        posts.add(article);
    }

    public void addPost(User user, String title, String text, String tag) {
        if (user.getUserType() == UserType.VISITOR) {
            System.out.println("Invalid user type...");
            return;
        }

        Post article = new Post(user.getUserName(), title, text, tag);
        posts.add(article);
    }

    public boolean modifiedPost(User user, Post article, String title, String text) {
        if (!article.getAuthor().equals(user.getUserName())) {
            return false;
        }

        for (Post a : posts) {
            if (a == article) {
                a.modified(title, text);
                return true;
            }
        }

        return false;
    }

    public boolean modifiedPost(User user, Post article, String title, String text, String tag) {
        if (!article.getAuthor().equals(user.getUserName())) {
            return false;
        }

        for (Post a : posts) {
            if (a == article) {
                a.modified(title, text, tag);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Post> getAllPosts() {
        return posts;
    }
}
