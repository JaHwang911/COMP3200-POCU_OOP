package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

//정렬 방법
//작성 일시 내림차순
//작성 일시 오름차순
//수정 일시 내림차순
//수정 일시 오름차순
//제목(사전 순서) 오름차순
public class Blog {
    private ArrayList<Post> posts;
    private ArrayList<Post> filteredPosts;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.filteredPosts = new ArrayList<>(128);
    }

    // Get post
    public ArrayList<Post> getAllPosts() {
        if (filteredPosts.size() > 0) {
            return filteredPosts;
        }
        
        return this.posts;
    }

    public ArrayList<Post> getPostsByAuthorOrNull(String authorName) {
        ArrayList<Post> resultPosts = new ArrayList<>(posts.size());

        for (Post p : this.posts) {
            if (p.getAuthor().equals(authorName)) {
                resultPosts.add(p);
            }
        }

        return resultPosts;
    }

    public ArrayList<Post> getPostsByTagOrNUll(String tag) {
        ArrayList<Post> resultPosts = new ArrayList<>(posts.size());

        for (Post p : posts) {
            if (p.getTag().equals(tag)) {
                resultPosts.add(p);
            }
        }

        return resultPosts;
    }

    // Add
    public void addPost(Post post) {
        posts.add(post);
    }

    // Set ordered type
    public void setPostsFilteredByTag(String tag) {
        filteredPosts.clear();

        for (Post p : this.posts) {
            if (p.getTag().equals(tag)) {
                this.filteredPosts.add(p);
            }
        }
    }

    public void setPostsFilteredByAuthor(String author) {
        filteredPosts.clear();

        for (Post p : this.posts) {
            if (p.getTag().equals(author)) {
                this.filteredPosts.add(p);
            }
        }
    }

    public void setPostsOrdered(OrderType type) {
        switch (type) {
            case NORMAL:
            case CREATED:
                setPostsByCreated();
                break;
            case CREATED_DESC:
                setPostsByCreatedDesc();
                break;
            case MODIFIED:
                setPostsByModified();
                break;
            case MODIFIED_DESC:
                setPostsByModifiedDesc();
                break;
            case TITLE:
                setPostsByTitle();
                break;
            default:
                assert (false) : "Unknown ordered type";
                break;
        }
    }

    private void setPostsByCreated() {
        Collections.sort(this.posts, (a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void setPostsByCreatedDesc() {
        Collections.sort(this.posts, (a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void setPostsByModified() {
        Collections.sort(this.posts, (a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void setPostsByModifiedDesc() {
        Collections.sort(this.posts, (a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void setPostsByTitle() {
        Collections.sort(this.posts, (a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
