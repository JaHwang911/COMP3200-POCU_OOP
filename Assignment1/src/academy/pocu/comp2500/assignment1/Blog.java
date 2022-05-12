package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

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
            return this.filteredPosts;
        }

        return this.posts;
    }

    public ArrayList<Post> getPostsByAuthor(String authorName) {
        ArrayList<Post> resultPosts = new ArrayList<>(posts.size());

        for (Post p : this.posts) {
            if (p.getAuthor().equals(authorName)) {
                resultPosts.add(p);
            }
        }

        return resultPosts;
    }

    public ArrayList<Post> getPostsByTag(String tag) {
        ArrayList<Post> resultPosts = new ArrayList<>(posts.size());

        for (Post p : this.posts) {
            ArrayList<String> tags = p.getTags();

            for (String t : tags) {
                if (t.equals(tag)) {
                    resultPosts.add(p);
                }
            }

        }

        return resultPosts;
    }

    // Add
    public void addPost(Post post) {
        this.posts.add(post);
    }

    // Set ordered type
    public void setPostsFilteredByTag(String tag) {
        this.filteredPosts.clear();

        for (Post p : this.posts) {
            ArrayList<String> tags = p.getTags();

            for (String t : tags) {
                if (t.equals(tag)) {
                    this.filteredPosts.add(p);
                }
            }
        }
    }

    public void setPostsFilteredByAuthor(String author) {
        this.filteredPosts.clear();

        for (Post p : this.posts) {
            if (p.getAuthor().equals(author)) {
                this.filteredPosts.add(p);
            }
        }
    }

    public void setPostsOrdered(OrderType type) {
        switch (type) {
            case NORMAL:
            case CREATED:
                this.setPostsByCreated();
                break;
            case CREATED_DESC:
                this.setPostsByCreatedDesc();
                break;
            case MODIFIED:
                this.setPostsByModified();
                break;
            case MODIFIED_DESC:
                this.setPostsByModifiedDesc();
                break;
            case TITLE:
                this.setPostsByTitle();
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
