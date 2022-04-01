package ru.netology

class PostNotFoundException(override val message: String?) : RuntimeException(message) {}
class CommentNotFoundException(override val message: String?) : RuntimeException(message) {}
class NoSuchReportReasonException(override val message: String?) : RuntimeException(message) {}