package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Blog {
    private final ArrayList<Post> posts;
    private final ArrayList<Post> filteredPosts;
    private OrderType orderType;
    private FilterType filterType;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.filteredPosts = new ArrayList<>(128);
        this.orderType = OrderType.NORMAL;
        this.filterType = FilterType.UNSET;
    }

    public ArrayList<Post> getPosts() {
        if (this.filterType == FilterType.UNSET) {
            for (Post p : this.posts) {
                this.filteredPosts.add(p);
            }
        }

        switch (this.orderType) {
            case NORMAL:
            case CREATED:
                sortByCreated();
                break;
            case CREATED_DESC:
                sortByCreatedDesc();
                break;
            case MODIFIED:
                sortByModified();
                break;
            case MODIFIED_DESC:
                sortByModifiedDesc();
                break;
            case TITLE:
                sortByTitle();
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
    public void setFilterByTag(String tagOrNull) {
        if (tagOrNull == null) {
            this.filterType = FilterType.UNSET;
            return;
        }

        switch (this.filterType) {
            case UNSET:
                this.filterType = FilterType.TAG;
                this.filteredPosts.clear();
            case TAG:
                for (Post p : this.posts) {
                    ArrayList<String> tags = p.getTags();

                    for (String t : tags) {
                        if (t.equals(tagOrNull)) {
                            this.filteredPosts.add(p);
                            break;
                        }
                    }
                }
                break;
            case AUTHOR:
            case COMBO:
                this.filterType = FilterType.COMBO;

                for (Post fp : this.filteredPosts) {
                    boolean hasTag = false;
                    var tags = fp.getTags();

                    for (String t : tags) {
                        if (t.equals(tagOrNull)) {
                            hasTag = true;
                            break;
                        }
                    }

                    if (hasTag == false) {
                        this.filteredPosts.remove(fp);
                    }
                }

                for (Post p : this.posts) {
                    var tags = p.getTags();

                    for (String t : tags) {
                        if (t.equals(tagOrNull)) {
                            this.filteredPosts.add(p);
                            break;
                        }
                    }
                }
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setFilterByAuthor(User userOrNull) {
        if (userOrNull == null) {
            this.filterType = FilterType.UNSET;
            return;
        }

        switch (this.filterType) {
            case UNSET:
            case AUTHOR:
                this.filterType = FilterType.AUTHOR;
                this.filteredPosts.clear();

                for (Post p : this.posts) {
                    if (p.getAuthor().equals(userOrNull)) {
                        this.filteredPosts.add(p);
                    }
                }
                break;
            case TAG:
                this.filterType = FilterType.COMBO;

                for (Post fp : this.filteredPosts) {
                    if (!fp.getAuthor().equals(userOrNull)) {
                        this.filteredPosts.remove(fp);
                    }
                }
                break;
        }
    }

    public void setSorted(OrderType type) {
        this.orderType = type;
    }

    private void sortByCreated() {
        this.filteredPosts.sort((a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void sortByCreatedDesc() {
        this.filteredPosts.sort((a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void sortByModified() {
        this.filteredPosts.sort((a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void sortByModifiedDesc() {
        this.filteredPosts.sort((a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void sortByTitle() {
        this.filteredPosts.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
