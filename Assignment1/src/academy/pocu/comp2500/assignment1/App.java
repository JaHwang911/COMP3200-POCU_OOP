package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.registry.Registry;

public class App {
    public App(Registry registry) {
        registry.registerBlogCreator("Blog");
        registry.registerTagFilterSetter("Blog", "setFilterOnOffByTags");
        registry.registerAuthorFilterSetter("Blog", "setFilterOnOffByAuthor");
        registry.registerPostOrderSetter("Blog", "setOrderType");
        registry.registerPostListGetter("Blog", "getPosts");
        registry.registerPostAdder("Blog", "addPost");
        registry.registerPostTitleUpdater("Post", "modifyTitle");
        registry.registerPostBodyUpdater("Post", "modifyBody");
        registry.registerPostTagAdder("Post", "addTags");
        registry.registerCommentAdder("Post", "addComment");
        registry.registerSubcommentAdder("Comment", "addSubcomment");
        registry.registerCommentUpdater("Comment", "modifyComment");
        registry.registerSubcommentUpdater("Comment", "modifyComment");
        registry.registerReactionAdder("Post", "addReaction");
        registry.registerReactionRemover("Post", "removeReaction");
        registry.registerCommentUpvoter("Comment", "addUpvote");
        registry.registerCommentDownvoter("Comment", "addDownvote");
        registry.registerCommentListGetter("Post", "getComments");
        registry.registerSubcommentListGetter("Comment", "getSubcomments");
        registry.registerSubcommentUpvoter("Comment", "addUpvote");
        registry.registerSubcommentDownvoter("Comment", "addDownvote");
    }
}
