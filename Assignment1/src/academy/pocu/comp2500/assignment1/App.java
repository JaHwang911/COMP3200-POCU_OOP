package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.registry.Registry;

public class App {
    public App(Registry registry) {
        registry.registerBlogCreator("Blog");
        registry.registerTagFilterSetter("Blog", "setPostsFilteredByTag");
        registry.registerAuthorFilterSetter("Blog", "setPostsFilteredByAuthor");
        registry.registerPostOrderSetter("Blog", "setPostsOrdered");
        registry.registerPostListGetter("Blog", "getAllPosts");
        registry.registerPostAdder("Blog", "addPost");
        registry.registerPostTitleUpdater("Blog", "modifyPostTitle");
        registry.registerPostBodyUpdater("Blog", "modifyPostBody");
        registry.registerPostTagAdder("Post", "addTag");
        registry.registerCommentAdder("Post", "addComment");
        registry.registerSubcommentAdder("Comment", "addSubcomment");
        registry.registerCommentUpdater("Comment", "modifyComment");
        registry.registerSubcommentUpdater("Comment", "modifyComment");
        registry.registerReactionAdder("Post", "addReaction");
        registry.registerReactionRemover("Post", "removeReaction");
        registry.registerCommentUpvoter("Comment", "addUpvote");
        registry.registerCommentDownvoter("Comment", "addDownvote");
        registry.registerCommentListGetter("Post", "getAllComments");
        registry.registerSubcommentListGetter("Comment", "getAllSubcomments");
        registry.registerSubcommentUpvoter("Comment", "addUpvote");
        registry.registerSubcommentDownvoter("Comment", "addDownvote");
    }
}
