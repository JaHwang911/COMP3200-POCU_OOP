package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.registry.Registry;

public class App {
    public App(Registry registry) {
        registry.registerBlogCreator("Blog");
        registry.registerTagFilterSetter("Blog", "setPostsByTag");
        registry.registerAuthorFilterSetter("Blog", "setPostsByAuthor");
        registry.registerPostOrderSetter("Blog", "setPostsOrdered");
        registry.registerPostListGetter("Blog", "getPosts");
        registry.registerPostAdder("Blog", "addPost");
        registry.registerPostTitleUpdater("Post", "updateTitle");
        registry.registerPostBodyUpdater("Post", "updateBody");
        registry.registerPostTagAdder("Post", "addTag");
        registry.registerCommentAdder("Post", "addComment");
        registry.registerSubcommentAdder("Comment", "addSubcomment");
        registry.registerCommentUpdater("Comment", "updateComment");
        registry.registerSubcommentUpdater("Comment", "updateComment");
        registry.registerReactionAdder("Post", "addReaction");
        registry.registerReactionRemover("Post", "removeReaction");
        registry.registerCommentUpvoter("Comment", "addUpvote");
        registry.registerCommentDownvoter("Comment", "addDownvote");
        registry.registerCommentListGetter("Post", "getComments");
        registry.registerSubcommentListGetter("Post", "getSubcomments");
        registry.registerSubcommentUpvoter("Comment", "addUpvote");
        registry.registerSubcommentDownvoter("Comment", "addDownvote");
    }
}
