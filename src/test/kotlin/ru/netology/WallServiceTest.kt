package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun prepare() {
        WallService.clearWall()
    }


    @Test
    fun addIdChanged() {
        val expectedId = 1
        val service = WallService.add(
            Post(
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my first post"
            )
        )
        assertEquals(expectedId, service.id)
    }

    @Test
    fun repostIdExists() {
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
        val actualResult = WallService.repost(
            Post(
                id = 2,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertTrue(actualResult)
    }

    @Test
    fun repostIdNotExists() {
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
        val actualResult = WallService.repost(
            Post(
                id = 5,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertFalse(actualResult)
    }

    @Test
    fun repostOnRepost() {
        WallService.add(
            Post(
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my second post"
            )
        )
        WallService.repost(
            Post(
                id = 1,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my third post"
            )
        )
        val actualResult = WallService.repost(
            Post(
                id = 2,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertTrue(actualResult)
    }

    @Test
    fun updatePostIdExists() {
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
        val actualResult = WallService.updatePost(
            Post(
                id = 2,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertTrue(actualResult)
    }

    @Test
    fun updatePostIdNotExists() {
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
        val actualResult = WallService.updatePost(
            Post(
                id = 4,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertFalse(actualResult)
    }

    @Test
    fun getAttachmentsExists() {
        WallService.add(
            Post(
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my fourth post",
                attachments = arrayOf(Audio(id = 1), Video(id = 2), Photo(id = 3))
            )
        )
        val actualResult = WallService.getAttachments(
            Post(
                id = 1,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertTrue(actualResult is Array<Attachments>)
    }

    @Test
    fun getAttachmentsNotExists() {
        WallService.add(
            Post(
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my fourth post",
            )
        )
        val actualResult = WallService.getAttachments(
            Post(
                id = 1,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertTrue(actualResult == null)
    }

    @Test
    fun getAttachmentsIdNotExists() {
        WallService.add(
            Post(
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my fourth post",
                attachments = arrayOf(Audio(id = 1), Video(id = 2), Photo(id = 3))
            )
        )
        val actualResult = WallService.getAttachments(
            Post(
                id = 2,
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "post with id=2 updated"
            )
        )
        assertTrue(actualResult == null)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentShouldThrow() {
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
    }

    @Test
    fun createCommentExist() {
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
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 1 to post 1"
            )
        )
        assertTrue(true)
    }

    @Test
    fun createCommentSecond() {
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
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 1 to post 1"
            )
        )
        WallService.createComment(
            Comment(
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 2 to post 1"
            )
        )
        assertTrue(true)
    }

    @Test(expected = NoSuchReportReasonException::class)
    fun reportCommentNotExistReason() {
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
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 1 to post 1"
            )
        )
        WallService.reportComment(1,10)
    }
    @Test(expected = CommentNotFoundException::class)
    fun reportCommentNotExistComment() {
        WallService.add(
            Post(
                ownerId = 1,
                fromId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "my fourth post"
            )
        )
        WallService.createComment(
            Comment(
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 1 to post 1"
            )
        )
        WallService.reportComment(2,0)
    }
    @Test
    fun reportCommentDone() {
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
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 1 to post 1"
            )
        )
        WallService.reportComment(1,0)
        assertTrue(true)
    }
    @Test
    fun reportCommentDoneMore() {
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
                postId = 1,
                date = System.currentTimeMillis().toInt(),
                text = "Comment 1 to post 1"
            )
        )
        WallService.reportComment(1,0)
        WallService.reportComment(1,3)
        assertTrue(true)
    }
}