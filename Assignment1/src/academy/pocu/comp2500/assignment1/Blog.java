package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Blog {
    private final ArrayList<Post> posts;
    private final ArrayList<Post> filteredPosts;
    private OrderType orderType;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.filteredPosts = new ArrayList<>(128);
        orderType = OrderType.NORMAL;
    }

    public ArrayList<Post> getPosts() {
        switch (this.orderType) {
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
            case TAG:
            case AUTHOR:
                break;
            default:
                assert false : "Unknown order type";
                break;
        }

        return this.filteredPosts;
    }

    public boolean addPost(Post post) {
        if (post.getAuthor().getUserType() == UserType.VISITOR) {
            return false;
        }

        this.posts.add(post);
        return true;
    }

    public boolean removePost(User user, Post post) {
        if (!user.equals(post.getAuthor())) {
            return false;
        }

        for (Post p : this.posts) {
            if (p.equals(post)) {
                this.posts.remove(post);
                return true;
            }
        }

        return false;
    }

    // Set ordered type
    public void setPostsByTag(String tag) {
        if (orderType != OrderType.AUTHOR) {
            this.filteredPosts.clear();
        }

        for (Post p : this.posts) {
            ArrayList<String> tags = p.getTags();

            for (String t : tags) {
                if (t.equals(tag)) {
                    this.filteredPosts.add(p);
                }
            }
        }

        this.orderType = OrderType.TAG;
    }

    public void setPostsByAuthor(User user) {
        if (orderType != OrderType.TAG) {
            this.filteredPosts.clear();
        }

        for (Post p : this.posts) {
            if (p.getAuthor().equals(user)) {
                this.filteredPosts.add(p);
            }
        }

        this.orderType = OrderType.AUTHOR;
    }

    public void setPostsOrdered(OrderType type) {
        this.orderType = type;
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
