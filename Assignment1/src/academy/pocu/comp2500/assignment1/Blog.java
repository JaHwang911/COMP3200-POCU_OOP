package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Blog {
    private final ArrayList<Post> post;
    private final ArrayList<Post> filteredpost;
    private OrderType orderType;
    private FilterType filterType;

    public Blog() {
        this.post = new ArrayList<>(128);
        this.filteredpost = new ArrayList<>(128);
        this.orderType = OrderType.NORMAL;
        this.filterType = FilterType.UNSET;
    }

    public ArrayList<Post> getPost() {
        if (this.filterType == FilterType.UNSET && filteredpost.size() == 0) {
            for (Post p : this.post) {
                this.filteredpost.add(p);
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

        return this.filteredpost;
    }

    public boolean addPost(Post post) {
        this.post.add(post);
        return true;
    }

    public boolean removePost(String userName, Post post) {
        if (!userName.equals(post.getUserName())) {
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
                this.filteredpost.clear();
            case TAG:
                for (Post p : this.post) {
                    if (p.hasTag(tagOrNull)) {
                        this.filteredpost.add(p);
                    }
                }
                break;
            case AUTHOR:
            case COMBO:
                this.filterType = FilterType.COMBO;

                for (Post fp : this.filteredpost) {
                    if (!fp.hasTag(tagOrNull)) {
                        this.filteredpost.remove(fp);
                    }
                }

                for (Post p : this.post) {
                    if (p.hasTag(tagOrNull)) {
                        this.filteredpost.add(p);
                        break;
                    }
                }
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setFilterByAuthor(String userNameOrNull) {
        if (userNameOrNull == null) {
            this.filterType = FilterType.UNSET;
            return;
        }

        switch (this.filterType) {
            case UNSET:
            case AUTHOR:
                this.filterType = FilterType.AUTHOR;
                this.filteredpost.clear();

                for (Post p : this.post) {
                    if (p.getUserName().equals(userNameOrNull)) {
                        this.filteredpost.add(p);
                    }
                }
                break;
            case TAG:
                this.filterType = FilterType.COMBO;

                for (Post fp : this.filteredpost) {
                    if (!fp.getUserName().equals(userNameOrNull)) {
                        this.filteredpost.remove(fp);
                    }
                }
                break;
        }
    }

    public void setSorted(OrderType type) {
        this.orderType = type;
    }

    private void sortByCreated() {
        this.filteredpost.sort((a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void sortByCreatedDesc() {
        this.filteredpost.sort((a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void sortByModified() {
        this.filteredpost.sort((a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void sortByModifiedDesc() {
        this.filteredpost.sort((a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void sortByTitle() {
        this.filteredpost.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
