package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Blog {
    private final ArrayList<Post> posts;
    private final ArrayList<Post> filteredPosts;
    private boolean isFiltered;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.filteredPosts = new ArrayList<>(128);
        this.isFiltered = false;
    }

    public boolean addPost(Post post) {
        if (post.getAuthor().getUserType() == UserType.VISITOR) {
            return false;
        }

        for (Post p : this.posts) {
            if (p.equals(post)) {
                return false;
            }
        }

        this.posts.add(post);

        return true;
    }

    public ArrayList<Post> getPosts() {
        if (!isFiltered) {
            return this.posts;
        }

        return this.filteredPosts;
    }

    public ArrayList<Post> getPostsByAuthor(User user) {
        ArrayList<Post> resultPosts = new ArrayList<>(posts.size());

        for (Post p : this.posts) {
            if (p.getAuthor().equals(user)) {
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

    public boolean removePost(User user, Post post) {
        if (!user.equals(post.getAuthor())) {
            return false;
        }

        for (Post p : this.posts) {
            if (p.equals(post)) {
                this.posts.remove(p);
                return true;
            }
        }

        return false;
    }

    // Set ordered type
    public void setPostsFilteredByTag(ArrayList<String> filterTags) {
        this.filteredPosts.clear();

        for (Post p : this.posts) {
            ArrayList<String> tags = p.getTags();

            for (String t : tags) {
                for (String ft : filterTags) {
                    if (t.equals(ft)) {
                        this.filteredPosts.add(p);
                    }
                }
            }
        }

        this.isFiltered = true;
    }

    public void setPostsFilteredByAuthor(User user) {
        this.filteredPosts.clear();

        for (Post p : this.posts) {
            if (p.getAuthor().equals(user)) {
                this.filteredPosts.add(p);
            }
        }

        this.isFiltered = true;
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

        this.isFiltered = true;
    }

    private void fillFilteredPost() {
        filteredPosts.clear();

        for (Post p : this.posts) {
            filteredPosts.add(p);
        }
    }

    private void setPostsByCreated() {
        fillFilteredPost();
        Collections.sort(this.filteredPosts, (a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void setPostsByCreatedDesc() {
        fillFilteredPost();
        Collections.sort(this.filteredPosts, (a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void setPostsByModified() {
        fillFilteredPost();
        Collections.sort(this.filteredPosts, (a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void setPostsByModifiedDesc() {
        fillFilteredPost();
        Collections.sort(this.filteredPosts, (a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void setPostsByTitle() {
        fillFilteredPost();
        Collections.sort(this.filteredPosts, (a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
