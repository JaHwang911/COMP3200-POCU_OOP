package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Blog {
    private final ArrayList<Post> posts;
    private OrderType orderType;
    private FilterType filterType;
    private final ArrayList<String> filteredTags;
    private String filteredAuthor;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.orderType = OrderType.NORMAL;
        this.filterType = FilterType.UNSET;
        this.filteredTags = new ArrayList<>(16);
        this.filteredAuthor = null;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> resultPosts = new ArrayList<>(this.posts.size());

        switch (filterType) {
            case UNSET:
                assert this.filteredTags.size() == 0;
                assert this.filteredAuthor == null;

                resultPosts.addAll(this.posts);
                break;
            case TAG:
                assert this.filteredTags.size() != 0;
                assert this.filteredAuthor == null;

                for (Post p : this.posts) {
                    var tags = p.getTags();

                    for (String t : tags) {
                        if (this.filteredTags.contains(t)) {
                            resultPosts.add(p);
                            break;
                        }
                    }
                }
                break;
            case AUTHOR:
                assert this.filteredTags.size() == 0;
                assert this.filteredAuthor != null;

                for (Post p : this.posts) {
                    if (p.getName().equals(filteredAuthor)) {
                        resultPosts.add(p);
                    }
                }
                break;
            case COMBO:
                assert this.filteredTags.size() != 0;
                assert this.filteredAuthor != null;

                for (Post p : this.posts) {
                    var tags = p.getTags();

                    for (String t : tags) {
                        if (this.filteredTags.contains(t)) {
                            resultPosts.add(p);
                            break;
                        }
                    }
                }

                for (Post p : resultPosts) {
                    if (!p.getName().equals(this.filteredAuthor)) {
                        resultPosts.remove(p);
                    }
                }
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }

        switch (this.orderType) {
            case NORMAL:
            case CREATED:
                sortByCreated(resultPosts);
                break;
            case CREATED_DESC:
                sortByCreatedDesc(resultPosts);
                break;
            case MODIFIED:
                sortByModified(resultPosts);
                break;
            case MODIFIED_DESC:
                sortByModifiedDesc(resultPosts);
                break;
            case TITLE:
                sortByTitle(resultPosts);
                break;
            default:
                assert false : "Unknown order type";
                break;
        }

        return resultPosts;
    }

    public boolean removePosts(String name, Post post) {
        if (!name.equals(post.getName())) {
            return false;
        }

        this.posts.remove(post);

        return false;
    }

    public void setFilterOnOffByTags(ArrayList<String> tags) {
        if (tags.size() == 0) {
            switch (this.filterType) {
                case COMBO:
                    this.filterType = FilterType.AUTHOR;
                    return;
                case TAG:
                    this.filterType = FilterType.UNSET;
                    this.filteredAuthor = null;
                    this.filteredTags.clear();
                    return;
                default:
                    return;
            }
        }

        switch (this.filterType) {
            case UNSET:
                this.filterType = FilterType.TAG;
            case TAG:
                this.filteredTags.addAll(tags);
                break;
            case AUTHOR:
            case COMBO:
                this.filterType = FilterType.COMBO;
                this.filteredTags.addAll(tags);
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setFilterOnOffByAuthor(String authorOrNull) {
        if (authorOrNull == null) {
            switch (this.filterType) {
                case COMBO:
                    this.filterType = FilterType.TAG;
                    return;
                case AUTHOR:
                    this.filterType = FilterType.UNSET;
                    this.filteredAuthor = null;
                    this.filteredTags.clear();
                    return;
                default:
                    return;
            }
        }

        switch (this.filterType) {
            case UNSET:
            case AUTHOR:
                this.filterType = FilterType.AUTHOR;
                this.filteredAuthor = authorOrNull;
                break;
            case TAG:
                this.filterType = FilterType.COMBO;
                this.filteredAuthor = authorOrNull;
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setOrderType(OrderType sortingType) {
        this.orderType = sortingType;
    }

    private void sortByCreated(ArrayList<Post> filteredPosts) {
        filteredPosts.sort((a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
    }

    private void sortByCreatedDesc(ArrayList<Post> filteredPosts) {
        filteredPosts.sort((a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
    }

    private void sortByModified(ArrayList<Post> filteredPosts) {
        filteredPosts.sort((a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
    }

    private void sortByModifiedDesc(ArrayList<Post> filteredPosts) {
        filteredPosts.sort((a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
    }

    private void sortByTitle(ArrayList<Post> filteredPosts) {
        filteredPosts.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
    }
}
