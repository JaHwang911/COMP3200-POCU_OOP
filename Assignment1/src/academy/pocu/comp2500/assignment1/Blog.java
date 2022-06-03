package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Blog {
    private final ArrayList<Post> posts;
    private SortType sortType;
    private FilterType filterType;
    private final ArrayList<String> filteringTags;
    private String filteringAuthor;

    public Blog() {
        this.posts = new ArrayList<>(128);
        this.sortType = SortType.NORMAL;
        this.filterType = FilterType.UNSET;
        this.filteringTags = new ArrayList<>(32);
        this.filteringAuthor = null;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> resultPosts = new ArrayList<>(this.posts.size());

        switch (filterType) {
            case UNSET:
                resultPosts.addAll(this.posts);
                break;
            case TAG:
                for (Post post : this.posts) {
                    ArrayList<String> tags = post.getTag();

                    for (String tag : tags) {
                        if (this.filteringTags.contains(tag)) {
                            resultPosts.add(post);
                            break;
                        }
                    }
                }
                break;
            case AUTHOR:
                for (Post post : this.posts) {
                    if (post.getAuthor().equals(this.filteringAuthor)) {
                        resultPosts.add(post);
                    }
                }
                break;
            case COMBO:
                for (Post post : this.posts) {
                    if (!post.getAuthor().equals(this.filteringAuthor)) {
                        continue;
                    }

                    ArrayList<String> tags = post.getTag();

                    for (String tag : tags) {
                        if (this.filteringTags.contains(tag)) {
                            resultPosts.add(post);
                            break;
                        }
                    }
                }
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }

        switch (this.sortType) {
            case NORMAL:
            case CREATED:
                resultPosts.sort((a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()));
                break;
            case CREATED_DESC:
                resultPosts.sort((a, b) -> a.getCreatedTime().compareTo(b.getCreatedTime()));
                break;
            case MODIFIED:
                resultPosts.sort((a, b) -> b.getModifiedTime().compareTo(a.getModifiedTime()));
                break;
            case MODIFIED_DESC:
                resultPosts.sort((a, b) -> a.getModifiedTime().compareTo(b.getModifiedTime()));
                break;
            case TITLE:
                resultPosts.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
                break;
            default:
                assert false : "Unknown order type";
                break;
        }

        return resultPosts;
    }

    public boolean removePosts(String name, Post post) {
        if (!name.equals(post.getAuthor())) {
            return false;
        }

        return this.posts.remove(post);
    }

    public void setFilterOnOffByTags(ArrayList<String> tags) {
        if (tags.size() == 0) {
            switch (this.filterType) {
                case TAG:
                    assert this.filteringAuthor == null;

                    this.filterType = FilterType.UNSET;
                    this.filteringTags.clear();
                    return;
                case COMBO:
                    assert this.filteringAuthor != null;

                    this.filterType = FilterType.AUTHOR;
                    this.filteringTags.clear();
                    return;
                default:
                    return;
            }
        }

        switch (this.filterType) {
            case UNSET:
            case TAG:
                this.filterType = FilterType.TAG;
                this.filteringTags.clear();
                this.filteringTags.addAll(tags);
                break;
            case AUTHOR:
            case COMBO:
                this.filterType = FilterType.COMBO;
                this.filteringTags.clear();
                this.filteringTags.addAll(tags);
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setFilterOnOffByAuthor(String authorOrNull) {
        if (authorOrNull == null) {
            switch (this.filterType) {
                case AUTHOR:
                    assert this.filteringTags.size() == 0;

                    this.filterType = FilterType.UNSET;
                    this.filteringAuthor = null;

                    return;
                case COMBO:
                    assert this.filteringTags.size() > 0;

                    this.filterType = FilterType.TAG;
                    this.filteringAuthor = null;

                    return;
                default:
                    return;
            }
        }

        switch (this.filterType) {
            case UNSET:
            case AUTHOR:
                this.filterType = FilterType.AUTHOR;
                this.filteringAuthor = authorOrNull;
                break;
            case TAG:
            case COMBO:
                this.filterType = FilterType.COMBO;
                this.filteringAuthor = authorOrNull;
                break;
            default:
                assert false : "Unknown filter type";
                break;
        }
    }

    public void setSortType(SortType sortingType) {
        this.sortType = sortingType;
    }
}
