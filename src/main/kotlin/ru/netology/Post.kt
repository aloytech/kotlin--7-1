package ru.netology

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int = 0,
    val date: Int,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendOnly: Boolean = false,
    val commentsCount: Int = 0,
    val copyright: String = "@aloytech",
    val likes: Likes = Likes(0, true, true, true),
    val reposts: Reposts = Reposts(0, false),
    val views: Views = Views(0),
    val postType: String = POSTTYPEPOST,
    val postSource: PostSource? = null,
    val geo: Geo? = null,
    val signerId: Int = 0,
    val copyHistory: Array<Post>? = null,
    val canPin: Boolean = true,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int = 0,
    val attachments: Array<Attachments>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)

data class Reposts(
    val count: Int,
    val userReposted: Boolean
)

data class Views(
    val count: Int
)

class PostSource()
class Geo()