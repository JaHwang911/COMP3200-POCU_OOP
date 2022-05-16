package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Blog {
    private final ArrayList<Post> post;
    private final ArrayList<Post> filteredPost;
    private OrderType orderType;
    private FilterType filterType;

    public Blog() {
        this.post = new ArrayList<>(128);
        this.filteredPost = new ArrayList<>(128);
        this.orderType = OrderType.NORMAL;
        this.filterType = FilterType.UNSET;
    }

    public void addPost(Post post) {
        this.post.add(post);
    }

    public ArrayList<Post> getPost() {
        if (this.filterType == FilterType.UNSET && filteredPost.size() == 0) {
            for (Post p : this.post) {
                this.filteredPost.add(p);
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

        return this.filteredPost;
    }

    public boolean removePost(String name, Post post) {
        if (!name.equals(post.getName())) {
            return false;
        }

        for (Post p : this.post) {
            if (p.equals(post)) {
                this.post.remove(post);
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
                this.filteredPost.clear();
            case TAG:
                for (Post p : this.post) {
                    if (p.hasTag(tagOrNull)) {
                        this.filteredPost.add(p);
                    }
                }
                break;
            case AUTHOR:
            case COMBO:
                this.filterType = FilterType.COMBO;

                for (Post fp : this.filteredPost) {
                    if (!fp.hasTag(tagOrNull)) {
                        this.filteredPost.remove(fp);
                    }
                }

                for (Post p : this.post) {
                    if (p.hasTag(tagOrNull)) {
                        this.filteredPost.add(p);
                        break;
                    }
                }
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setFilterByAuthor(String userOrNull) {
        if (userOrNull == null) {
            this.filterType = FilterType.UNSET;
            return;
        }

        switch (this.filterType) {
            case UNSET:
            case AUTHOR:
                this.filterType = FilterType.AUTHOR;
                this.filteredPost.clear();

                for (Post p : this.post) {
                    if (p.getName().equals(userOrNull)) {
                        this.filteredPost.add(p);
                    }
                }
                break;
            case TAG:
                this.filterType = FilterType.COMBO;

                for (Post fp : this.filteredPost) {
                    if (!fp.getName().equals(userOrNull)) {
                        this.filteredPost.remove(fp);
                    }
                }
                break;
        }
    }

    public void setOrderType(OrderType sortingType) {
        this.orderType = sortingType;
    }

    private void sortByCreated() {
        this.filteredPost.sort((a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void sortByCreatedDesc() {
        this.filteredPost.sort((a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void sortByModified() {
        this.filteredPost.sort((a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void sortByModifiedDesc() {
        this.filteredPost.sort((a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void sortByTitle() {
        this.filteredPost.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
