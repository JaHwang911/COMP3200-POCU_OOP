package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.user.User;
import academy.pocu.comp2500.assignment1.user.UserType;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        testAddPost();
        testGetByTag();
        testGetByAuthor();
        testGetByOrderedType();
        testAddComment();
        testAddSubcomment();
        testCommentUpvoteDownvote();
        testSubcommentUpvoteDownvote();
        testUpdatePost();
        testAddReaction();

        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob");
    }

    private static void testAddPost() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "computer");

        Post post2 = new Post(user1, "About Me", "I'm ...");
        post2.addTag(user1, "i my me mine");

        Post post3 = new Post(user1, "About event horizon", "Black hole is ...");
        post3.addTag(user1, "Space");

        blog.addPost(post1);
        blog.addPost(post2);
        blog.addPost(post3);

        ArrayList<Post> posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About CS");
        assert posts.get(1).getTitle().equals("About Me");
        assert posts.get(2).getTitle().equals("About event horizon");
    }

    private static void testGetByTag() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "Computer");

        Post post2 = new Post(user1, "About event horizon", "Black hole is ...");
        post2.addTag(user1, "Space");

        blog.addPost(post1);
        blog.addPost(post2);

        ArrayList<Post> posts = blog.getPostsByTagOrNUll("Space");

        assert posts.size() == 1;
        assert posts.get(0).getTitle().equals("About event horizon");

        posts = blog.getPostsByTagOrNUll("Android");

        assert posts.size() == 0;
    }

    private static void testGetByAuthor() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "Computer");

        Post post2 = new Post(user2, "About Unreal", "Unreal is ...");
        post2.addTag(user1, "Game");

        blog.addPost(post1);
        blog.addPost(post2);

        ArrayList<Post> posts = blog.getPostsByAuthorOrNull("Baro Kim");

        assert posts.size() == 1;
        assert posts.get(0).getTitle().equals("About Unreal");
        assert posts.get(0).getAuthor().equals("Baro Kim");

        posts = blog.getPostsByTagOrNUll("Junseok Kim");

        assert posts.size() == 0;
    }

    private static void testGetByOrderedType() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);
        User user3 = new User("Juneseok", "Kim", UserType.WRITER);

        Post post1 = new Post(user1, "About CS", "CS is ...");
        post1.addTag(user1, "Computer");

        Post post2 = new Post(user1, "About Me", "I'm ...");
        post1.addTag(user1, "I My Me Mine");

        Post post3 = new Post(user2, "About Unreal", "Unreal is ...");
        post3.addTag(user1, "Game");

        Post post4 = new Post(user2, "About Unity", "Unity is ...");
        post4.addTag(user1, "Game");

        Post post5 = new Post(user3, "About Android", "Android is ...");
        post5.addTag(user3, "mobile");

        Post post6 = new Post(user3, "About IOS", "IOS is ...");
        post6.addTag(user3, "mobile");

        blog.addPost(post1);
        blog.addPost(post2);
        blog.addPost(post3);
        blog.addPost(post4);
        blog.addPost(post5);
        blog.addPost(post6);

        // CREATE
        blog.setPostsOrdered(OrderType.CREATED);
        ArrayList<Post> posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About IOS");
        assert posts.get(1).getTitle().equals("About Android");
        assert posts.get(2).getTitle().equals("About Unity");
        assert posts.get(3).getTitle().equals("About Unreal");
        assert posts.get(4).getTitle().equals("About Me");
        assert posts.get(5).getTitle().equals("About CS");

        // CREATE_DESC
        blog.setPostsOrdered(OrderType.CREATED_DESC);
        posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About CS");
        assert posts.get(1).getTitle().equals("About Me");
        assert posts.get(2).getTitle().equals("About Unreal");
        assert posts.get(3).getTitle().equals("About Unity");
        assert posts.get(4).getTitle().equals("About Android");
        assert posts.get(5).getTitle().equals("About IOS");

        // MODIFIED
        blog.setPostsOrdered(OrderType.MODIFIED);
        posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About IOS");
        assert posts.get(1).getTitle().equals("About Android");
        assert posts.get(2).getTitle().equals("About Unity");
        assert posts.get(3).getTitle().equals("About Unreal");
        assert posts.get(4).getTitle().equals("About Me");
        assert posts.get(5).getTitle().equals("About CS");

        // MODIFIED_DESC
        blog.setPostsOrdered(OrderType.MODIFIED_DESC);
        posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About CS");
        assert posts.get(1).getTitle().equals("About Me");
        assert posts.get(2).getTitle().equals("About Unreal");
        assert posts.get(3).getTitle().equals("About Unity");
        assert posts.get(4).getTitle().equals("About Android");
        assert posts.get(5).getTitle().equals("About IOS");

        // TITLE
        blog.setPostsOrdered(OrderType.TITLE);
        posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About Android");
        assert posts.get(1).getTitle().equals("About CS");
        assert posts.get(2).getTitle().equals("About IOS");
        assert posts.get(3).getTitle().equals("About Me");
        assert posts.get(4).getTitle().equals("About Unity");
        assert posts.get(5).getTitle().equals("About Unreal");

        blog.setPostsOrdered(OrderType.NORMAL);
        posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About IOS");
        assert posts.get(1).getTitle().equals("About Android");
        assert posts.get(2).getTitle().equals("About Unity");
        assert posts.get(3).getTitle().equals("About Unreal");
        assert posts.get(4).getTitle().equals("About Me");
        assert posts.get(5).getTitle().equals("About CS");
    }

    private static void testAddComment() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);

        Post post1 = new Post(user1, "About Time", "Time is ...");
        post1.addComment(user2, "Awsome!!");

        blog.addPost(post1);
        ArrayList<Comment> comments = post1.getAllCommentsOrNULL();

        assert comments.size() == 1;
        assert comments.get(0).getAuthor().equals("Baro Kim");
    }

    private static void testAddSubcomment() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);

        Post post1 = new Post(user1, "About Time", "Time is ...");
        post1.addComment(user2, "Awsome!!");

        blog.addPost(post1);
        ArrayList<Comment> comments = post1.getAllCommentsOrNULL();
        Comment comment = comments.get(0);

        assert comments.size() == 1;
        assert comments.get(0).getAuthor().equals("Baro Kim");

        comment.addSubcomment(user1, "Thanks bro!");

        ArrayList<Subcomment> subcomment = comment.getAllSubcommentsOrNull();

        assert subcomment.size() == 1;
        assert subcomment.get(0).getComment().equals("Thanks bro!");
    }

    private static void testCommentUpvoteDownvote() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);

        Post post1 = new Post(user1, "About Time", "Time is ...");

        blog.addPost(post1);
        ArrayList<Post> posts = blog.getAllPosts();
        Post getPost1 = posts.get(0);

        getPost1.addComment(user2, "Awesome!!");
        getPost1.addComment(user2, "So so");

        ArrayList<Comment> comments = getPost1.getAllCommentsOrNULL();

        assert comments.size() == 2;

        Comment comment1 = comments.get(0);
        Comment comment2 = comments.get(1);

        comment1.addDownvote();
        comment2.addUpvote();

        comments = getPost1.getAllCommentsOrNULL();

        assert comments.get(0).getComment().equals("So so");
        assert comments.get(1).getComment().equals("Awesome!!");
    }

    private static void testSubcommentUpvoteDownvote() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);
        User user3 = new User("Juneseok", "lee", UserType.VISITOR);
        User user4 = new User("hyeon ho", "lee", UserType.VISITOR);

        Post post1 = new Post(user1, "About Time", "Time is ...");

        blog.addPost(post1);
        ArrayList<Post> posts = blog.getAllPosts();
        Post getPost1 = posts.get(0);

        getPost1.addComment(user2, "Awesome!!");

        ArrayList<Comment> comments = getPost1.getAllCommentsOrNULL();

        comments.get(0).addSubcomment(user4, "Umm.. I don't think so!!");
        comments.get(0).addSubcomment(user3, "Yeah, I think so!!");

        ArrayList<Subcomment> subcomments = comments.get(0).getAllSubcommentsOrNull();

        for (Subcomment s : subcomments) {
            System.out.println(s.getComment());
        }

        subcomments.get(0).addDownvote();
        subcomments.get(1).addUpvote();

        subcomments = comments.get(0).getAllSubcommentsOrNull();

        assert subcomments.get(0).getComment().equals("Yeah, I think so!!");
        assert subcomments.get(1).getComment().equals("Umm.. I don't think so!!");
    }

    private static void testUpdatePost() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);
        Post post1 = new Post(user1, "About Me", "I'm ...");

        post1.modifyTitle(user2, "About Unreal");

        blog.addPost(post1);
        ArrayList<Post> posts = blog.getAllPosts();
        Post post = posts.get(0);

        post.modifyTitle(user1, "About Math");
        post.modifyBody(user1, "Math is ...");

        posts = blog.getAllPosts();

        assert posts.get(0).getTitle().equals("About Math");
        assert posts.get(0).getBody().equals("Math is ...");
    }

    private static void testAddReaction() {
        Blog blog = new Blog();
        User user1 = new User("Ja", "Hwang", UserType.WRITER);
        User user2 = new User("Baro", "Kim", UserType.WRITER);
        User user3 = new User("Junesoek", "Lee", UserType.VISITOR);
        Post post1 = new Post(user1, "About Me", "I'm ...");

        blog.addPost(post1);
        ArrayList<Post> posts = blog.getAllPosts();
        Post post = posts.get(0);

        post.addReaction(user2, ReactionType.GREAT);
        post.addReaction(user3, ReactionType.FUN);

        posts = blog.getAllPosts();

        assert posts.get(0).getAllReactions().get(0).getType() == ReactionType.GREAT;
        assert posts.get(0).getAllReactions().get(1).getType() == ReactionType.FUN;
    }
}
