package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Blog {
    private final ArrayList<Post> posts;
    private final ArrayList<Post> filteredPosts;
    private OrderType orderType;
    private FilterType filterType;
    private final ArrayList<String> filteredTags;
    private User filteredAuthor;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.filteredPosts = new ArrayList<>(128);
        this.orderType = OrderType.NORMAL;
        this.filterType = FilterType.UNSET;
        this.filteredTags = new ArrayList<>(8);
        this.filteredAuthor = null;
    }

    public ArrayList<Post> getPosts() {
        fillFilteredPost();

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
    public void setFilterByTag(boolean isSet, String tag) {
        if (!isSet) {
            this.filterType = FilterType.UNSET;
            return;
        }

        if (this.filterType == FilterType.UNSET) {
            this.filterType = FilterType.TAG;
            this.filteredPosts.clear();
        } else if (this.filterType == FilterType.AUTHOR) {
            this.filterType = FilterType.COMBO;
        }

        this.filteredTags.add(tag);
        for (Post p : this.posts) {
            ArrayList<String> tags = p.getTags();

            for (String t : tags) {
                if (t.equals(tag)) {
                    this.filteredPosts.add(p);
                }
            }
        }
    }

    public void setFilterByAuthor(boolean isSet, User user) {
        if (!isSet) {
            this.filterType = FilterType.UNSET;
            return;
        }

        if (this.filterType == FilterType.UNSET) {
            this.filterType = FilterType.AUTHOR;
            this.filteredPosts.clear();
        } else if (this.filterType == FilterType.TAG) {
            this.filterType = FilterType.COMBO;
        }

        for (Post p : this.posts) {
            if (p.getAuthor().equals(user)) {
                this.filteredPosts.add(p);
            }
        }
    }

    public void setPostsOrdered(OrderType type) {
        this.orderType = type;
    }

    private void fillFilteredPost() {
        filteredPosts.clear();
    }

    private void setPostsByCreated() {
        Collections.sort(this.filteredPosts, (a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void setPostsByCreatedDesc() {
        Collections.sort(this.filteredPosts, (a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void setPostsByModified() {
        Collections.sort(this.filteredPosts, (a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void setPostsByModifiedDesc() {
        Collections.sort(this.filteredPosts, (a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void setPostsByTitle() {
        Collections.sort(this.filteredPosts, (a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
