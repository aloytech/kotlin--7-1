package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reportsComment = emptyArray<ReportComment>()

    fun reportComment(commentId:Int, reason: Int){
        val existComment = getCommentById(commentId)
            ?: throw CommentNotFoundException("Comment with id: $commentId not exist")
        val currentId: Int = when {
            reportsComment.isEmpty() -> 1
            else -> reportsComment.last().id + 1
        }
        if (reason in 0..7){
            reportsComment+=ReportComment(currentId, THISUSER, existComment.id,reason)
        } else{
            throw NoSuchReportReasonException("Reason must be Int in 0..7")
        }
    }

    fun createComment(comment: Comment) {
        val existPost = getPostById(comment.postId)
            ?: throw PostNotFoundException("Post with id: ${comment.postId} not exist")
        val currentId: Int = when {
            comments.isEmpty() -> 1
            else -> comments.last().id + 1
        }
        val newCommentsCount = existPost.commentsCount + 1
        updatePost(existPost.copy(commentsCount = newCommentsCount))
        val countedComment = comment.copy(id = currentId)
        comments += countedComment
    }

    fun add(post: Post): Post {
        val currentId: Int = when {
            posts.isEmpty() -> 1
            else -> posts.last().id + 1
        }
        val countedPost = post.copy(id = currentId)
        posts += countedPost
        return posts.last()
    }

    fun repost(post: Post): Boolean {
        var history: Array<Post> = post.copyHistory ?: emptyArray<Post>()
        history += post
        val originalPostCountReposts = post.reposts.count
        val changedPost = post.copy(reposts = Reposts(originalPostCountReposts + 1, true))
        if (updatePost(changedPost)) {
            val repost = post.copy(copyHistory = history)
            add(repost)
            return true
        } else {
            return false
        }
    }

    fun updatePost(post: Post): Boolean {
        var exist = false
        for ((index, existPost) in posts.withIndex()) {
            if (existPost.id == post.id) {
                val updatedPost = post.copy(date = existPost.date, ownerId = existPost.ownerId)
                posts[index] = updatedPost
                exist = true
            }
        }
        return exist
    }


    fun outWall(): String {
        val outString: StringBuilder = java.lang.StringBuilder()

        for (post in posts) {
            val isRepost = when (post.copyHistory) {
                null -> "original"
                else -> "repost"
            }
            outString.append(
                "\n" +
                        post.id + " " + post.text + " reposted:" + post.reposts.count +
                        " " + isRepost
            )
        }
        return outString.toString()
    }

    fun clearWall() {
        posts = emptyArray<Post>()
        comments = emptyArray<Comment>()
        reportsComment = emptyArray<ReportComment>()
    }

    fun getAttachments(post: Post): Array<Attachments>? {
        for (existPost in posts) {
            if (existPost.id == post.id) {
                return existPost.attachments
            }
        }
        return null
    }

    private fun getPostById(id: Int): Post? {
        for (existPost in posts) {
            if (existPost.id == id) {
                return existPost
            }
        }
        return null
    }
    private fun getCommentById(id: Int): Comment? {
        for (existComment in comments) {
            if (existComment.id == id) {
                return existComment
            }
        }
        return null
    }
    private fun getReasonByCommentId(commentId: Int): String? {
        for (existReport in reportsComment) {
            if (existReport.commentId == commentId) {
                val reason: String? = when(existReport.reason){
                    0 -> "Spam"
                    1 -> "Kid porno"
                    2 -> "Extremism"
                    3 -> "Violence"
                    4 -> "Drugs"
                    5 -> "18+"
                    6 -> "Insult"
                    7 -> "Suicide"
                    else -> null
                }
                return reason
            }
        }
        return null
    }

    fun outCommentsByPostId(id: Int) {
        val existPost = getPostById(id)
            ?: throw PostNotFoundException("Post with id: $id not exist")
        if (existPost.commentsCount == 0) {
            println("Post $id has no comments")
        } else {
            println ("Post $id has ${existPost.commentsCount} комментариев")
            for (comment in comments) {
                if (comment.postId == id) {
                    println("id ${comment.id} ${comment.text} ")
                }
            }
        }
    }
}