package ru.netology

const val POSTTYPEPOST = "post"
const val THISUSER = 1

fun main() {
    WallService.add(
        Post(
            ownerId = 1,
            fromId = 1,
            date = System.currentTimeMillis().toInt(),
            text = "my first post"
        )
    )
    WallService.add(
        Post(
            ownerId = 1,
            fromId = 1,
            date = System.currentTimeMillis().toInt(),
            text = "my second post"
        )
    )
    WallService.add(
        Post(
            ownerId = 1,
            fromId = 1,
            date = System.currentTimeMillis().toInt(),
            text = "my third post"
        )
    )
    WallService.add(
        Post(
            ownerId = 1,
            fromId = 1,
            date = System.currentTimeMillis().toInt(),
            text = "my fourth post",
        )
    )
    WallService.createComment(
        Comment(
            postId = 4,
            date = System.currentTimeMillis().toInt(),
            text = "Comment 1 to post 4"
        )
    )
    WallService.createComment(
        Comment(
            postId = 4,
            date = System.currentTimeMillis().toInt(),
            text = "Comment 2 to post 4"
        )
    )
    WallService.outCommentsByPostId(4)
}




