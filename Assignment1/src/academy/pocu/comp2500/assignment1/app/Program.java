package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
//        testAddPost();
//        testGetByTag();
//        testGetByAuthor();
//        testGetByOrderedType();
//        testAddComment();
//        testAddSubcomment();
//        testCommentUpvoteDownvote();
//        testSubcommentUpvoteDownvote();
//        testUpdatePost();
//        testAddReaction();

        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob");
    }

//    private static void testAddPost() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//
//        Post post1 = new Post(user1.getUserName(), "About CS", "CS is ...");
//        post1.addTag(user1.getUserName(), "computer");
//
//        Post post2 = new Post(user1.getUserName(), "About Me", "I'm ...");
//        post2.addTag(user1.getUserName(), "i my me mine");
//
//        Post post3 = new Post(user1.getUserName(), "About event horizon", "Black hole is ...");
//        post3.addTag(user1.getUserName(), "Space");
//
//        assert blog.addPost(user1, post1);
//        assert blog.addPost(user1, post2);
//        assert blog.addPost(user1, post3);
//
//        ArrayList<Post> posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About CS");
//        assert posts.get(1).getTitle().equals("About Me");
//        assert posts.get(2).getTitle().equals("About event horizon");
//    }
//
//    private static void testGetByTag() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//
//        Post post1 = new Post(user1.getUserName(), "About CS", "CS is ...");
//        post1.addTag(user1.getUserName(), "Computer");
//
//        Post post2 = new Post(user1.getUserName(), "About event horizon", "Black hole is ...");
//        post2.addTag(user1.getUserName(), "Space");
//
//        assert blog.addPost(user1, post1);
//        assert blog.addPost(user1, post2);
//
//        ArrayList<Post> posts = blog.getPostsByTag("Space");
//
//        assert posts.size() == 1;
//        assert posts.get(0).getTitle().equals("About event horizon");
//
//        posts = blog.getPostsByTag("Android");
//
//        assert posts.size() == 0;
//    }
//
//    private static void testGetByAuthor() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//
//        Post post1 = new Post(user1.getUserName(), "About CS", "CS is ...");
//        post1.addTag(user1.getUserName(), "Computer");
//
//        Post post2 = new Post(user2.getUserName(), "About Unreal", "Unreal is ...");
//        post2.addTag(user1.getUserName(), "Game");
//
//        assert blog.addPost(user1, post1);
//        assert blog.addPost(user2, post2);
//        assert !blog.addPost(user2, post1);
//
//        ArrayList<Post> posts = blog.getPostsByAuthor("Baro Kim");
//
//        assert posts.size() == 1;
//        assert posts.get(0).getTitle().equals("About Unreal");
//        assert posts.get(0).getAuthor().equals("Baro Kim");
//
//        posts = blog.getPostsByTag("Junseok Kim");
//
//        assert posts.size() == 0;
//    }
//
//    private static void testGetByOrderedType() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//        User user3 = new User("Juneseok", "Kim", UserType.WRITER);
//
//        Post post1 = new Post(user1.getUserName(), "About CS", "CS is ...");
//        post1.addTag(user1.getUserName(), "Computer");
//
//        Post post2 = new Post(user1.getUserName(), "About Me", "I'm ...");
//        post1.addTag(user1.getUserName(), "I My Me Mine");
//
//        Post post3 = new Post(user2.getUserName(), "About Unreal", "Unreal is ...");
//        post3.addTag(user1.getUserName(), "Game");
//
//        Post post4 = new Post(user2.getUserName(), "About Unity", "Unity is ...");
//        post4.addTag(user1.getUserName(), "Game");
//
//        Post post5 = new Post(user3.getUserName(), "About Android", "Android is ...");
//        post5.addTag(user3.getUserName(), "mobile");
//
//        Post post6 = new Post(user3.getUserName(), "About IOS", "IOS is ...");
//        post6.addTag(user3.getUserName(), "mobile");
//
//        assert blog.addPost(user1, post1);
//        assert blog.addPost(user1, post2);
//        assert blog.addPost(user2, post3);
//        assert blog.addPost(user2, post4);
//        assert blog.addPost(user3, post5);
//        assert blog.addPost(user3, post6);
//
//        // CREATE
//        blog.setPostsOrdered(OrderType.CREATED);
//        ArrayList<Post> posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About IOS");
//        assert posts.get(1).getTitle().equals("About Android");
//        assert posts.get(2).getTitle().equals("About Unity");
//        assert posts.get(3).getTitle().equals("About Unreal");
//        assert posts.get(4).getTitle().equals("About Me");
//        assert posts.get(5).getTitle().equals("About CS");
//
//        // CREATE_DESC
//        blog.setPostsOrdered(OrderType.CREATED_DESC);
//        posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About CS");
//        assert posts.get(1).getTitle().equals("About Me");
//        assert posts.get(2).getTitle().equals("About Unreal");
//        assert posts.get(3).getTitle().equals("About Unity");
//        assert posts.get(4).getTitle().equals("About Android");
//        assert posts.get(5).getTitle().equals("About IOS");
//
//        // MODIFIED
//        blog.setPostsOrdered(OrderType.MODIFIED);
//        posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About IOS");
//        assert posts.get(1).getTitle().equals("About Android");
//        assert posts.get(2).getTitle().equals("About Unity");
//        assert posts.get(3).getTitle().equals("About Unreal");
//        assert posts.get(4).getTitle().equals("About Me");
//        assert posts.get(5).getTitle().equals("About CS");
//
//        // MODIFIED_DESC
//        blog.setPostsOrdered(OrderType.MODIFIED_DESC);
//        posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About CS");
//        assert posts.get(1).getTitle().equals("About Me");
//        assert posts.get(2).getTitle().equals("About Unreal");
//        assert posts.get(3).getTitle().equals("About Unity");
//        assert posts.get(4).getTitle().equals("About Android");
//        assert posts.get(5).getTitle().equals("About IOS");
//
//        // TITLE
//        blog.setPostsOrdered(OrderType.TITLE);
//        posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About Android");
//        assert posts.get(1).getTitle().equals("About CS");
//        assert posts.get(2).getTitle().equals("About IOS");
//        assert posts.get(3).getTitle().equals("About Me");
//        assert posts.get(4).getTitle().equals("About Unity");
//        assert posts.get(5).getTitle().equals("About Unreal");
//
//        blog.setPostsOrdered(OrderType.NORMAL);
//        posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About IOS");
//        assert posts.get(1).getTitle().equals("About Android");
//        assert posts.get(2).getTitle().equals("About Unity");
//        assert posts.get(3).getTitle().equals("About Unreal");
//        assert posts.get(4).getTitle().equals("About Me");
//        assert posts.get(5).getTitle().equals("About CS");
//    }
//
//    private static void testAddComment() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.VISITOR);
//
//        Post post1 = new Post(user1.getUserName(), "About Time", "Time is ...");
//        post1.addComment(user2.getUserName(), "Awsome!!");
//
//        Post post2 = new Post(user2.getUserName(), "About Null", "Null is ...");
//
//        assert blog.addPost(user1, post1);
//        assert !blog.addPost(user2 ,post2);
//        ArrayList<Comment> comments = post1.getAllComments();
//
//        assert comments.size() == 1;
//        assert comments.get(0).getAuthor().equals("Baro Kim");
//    }
//
//    private static void testAddSubcomment() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//
//        Post post1 = new Post(user1.getUserName(), "About Time", "Time is ...");
//        post1.addComment(user2.getUserName(), "Awsome!!");
//
//        blog.addPost(user1, post1);
//        ArrayList<Comment> comments = post1.getAllComments();
//        Comment comment = comments.get(0);
//
//        assert comments.size() == 1;
//        assert comments.get(0).getAuthor().equals("Baro Kim");
//
//        comment.addSubcomment(user1.getUserName(), "Thanks bro!");
//
//        ArrayList<Comment> subcomment = comment.getAllSubcomments();
//
//        assert subcomment.size() == 1;
//        assert subcomment.get(0).getComment().equals("Thanks bro!");
//    }
//
//    private static void testCommentUpvoteDownvote() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//
//        Post post1 = new Post(user1.getUserName(), "About Time", "Time is ...");
//
//        blog.addPost(user1, post1);
//        ArrayList<Post> posts = blog.getAllPosts();
//        Post getPost1 = posts.get(0);
//
//        getPost1.addComment(user2.getUserName(), "Awesome!!");
//        getPost1.addComment(user2.getUserName(), "So so");
//
//        ArrayList<Comment> comments = getPost1.getAllComments();
//
//        assert comments.size() == 2;
//
//        Comment comment1 = comments.get(0);
//        Comment comment2 = comments.get(1);
//
//        assert (comment1.addDownvote(user1.getUserName()));
//        assert (comment2.addUpvote(user1.getUserName()));
//        assert (!comment1.addDownvote(user1.getUserName()));
//
//        comments = getPost1.getAllComments();
//
//        assert comments.get(0).getComment().equals("So so");
//        assert comments.get(1).getComment().equals("Awesome!!");
//    }
//
//    private static void testSubcommentUpvoteDownvote() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//        User user3 = new User("Juneseok", "lee", UserType.VISITOR);
//        User user4 = new User("hyeon ho", "lee", UserType.VISITOR);
//
//        Post post1 = new Post(user1.getUserName(), "About Time", "Time is ...");
//
//        blog.addPost(user1, post1);
//        ArrayList<Post> posts = blog.getAllPosts();
//        Post getPost1 = posts.get(0);
//
//        getPost1.addComment(user2.getUserName(), "Awesome!!");
//
//        ArrayList<Comment> comments = getPost1.getAllComments();
//
//        comments.get(0).addSubcomment(user4.getUserName(), "Umm.. I don't think so!!");
//        comments.get(0).addSubcomment(user3.getUserName(), "Yeah, I think so!!");
//
//        ArrayList<Comment> subcomments = comments.get(0).getAllSubcomments();
//
//        subcomments.get(0).addDownvote(user1.getUserName());
//        subcomments.get(1).addUpvote(user2.getUserName());
//
//        subcomments = comments.get(0).getAllSubcomments();
//
//        assert subcomments.get(0).getComment().equals("Yeah, I think so!!");
//        assert subcomments.get(1).getComment().equals("Umm.. I don't think so!!");
//    }
//
//    private static void testUpdatePost() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//
//        blog.addPost(user1, new Post(user1.getUserName(), "About Me", "I'm ..."));
//
//        ArrayList<Post> posts = blog.getAllPosts();
//        Post post = posts.get(0);
//
//        assert post.getTitle().equals("About Me");
//        assert post.getBody().equals("I'm ...");
//        assert post.getAuthor().equals("Ja Hwang");
//
//        post.modifyTitle(user1.getUserName(), "About Math");
//        post.modifyBody(user1.getUserName(), "Math is ...");
//
//        posts = blog.getAllPosts();
//
//        assert posts.get(0).getTitle().equals("About Math");
//        assert posts.get(0).getBody().equals("Math is ...");
//    }
//
//    private static void testAddReaction() {
//        Blog blog = new Blog();
//        User user1 = new User("Ja", "Hwang", UserType.WRITER);
//        User user2 = new User("Baro", "Kim", UserType.WRITER);
//        User user3 = new User("Junesoek", "Lee", UserType.VISITOR);
//
//        blog.addPost(user1, new Post(user1.getUserName(), "About Me", "I'm ..."));
//        ArrayList<Post> posts = blog.getAllPosts();
//        Post post = posts.get(0);
//
//        post.addReaction(user2.getUserName(), ReactionType.GREAT);
//        post.addReaction(user3.getUserName(), ReactionType.FUN);
//
//        post = blog.getAllPosts().get(0);
//
//        assert post.getReactionCount(ReactionType.GREAT) == 1;
//        assert post.getReactionCount(ReactionType.FUN) == 1;
//    }
}
