package ru.netology

interface Attachments {
    val type: String
    fun outputAccordingType() {
        println("attachment type is: $type")
    }
}

class Audio(
    override val type: String = "Audio",
    private val id: Int = 0,
    private val content: String = "MP3"
) : Attachments {
    override fun outputAccordingType() {
        super.outputAccordingType()
        println("It's time for music $id $content")
    }
}

class Video(
    override val type: String = "Video",
    private val id: Int = 0,
    private val content: String = "MP4",
    private val isAdult: Boolean = false
) : Attachments {
    override fun outputAccordingType() {
        super.outputAccordingType()
        if (isAdult) println("!!! 18+ only!!!")
        println("It's time for video $id $content")
    }
}

class Link(
    override val type: String = "Link",
    private val id: Int = 0,
    private val content: String = "http://mail.ru"
) : Attachments {
    override fun outputAccordingType() {
        super.outputAccordingType()
        println("Please follow the link $id $content")
    }
}

class Photo(
    override val type: String = "Photo",
    private val id: Int = 0,
    private val content: String = "JPG",
    private var size: String = "1280x1024"
) : Attachments {
    override fun outputAccordingType() {
        super.outputAccordingType()
        println("Please view picture $id $content $size")
    }

    fun resize(newSize: String) {
        size = newSize
    }
}

class Document(
    override val type: String = "Document",
    private val id: Int = 0,
    private val content: String = "file.doc",
    private val extension: String = "doc"
) : Attachments {
    override fun outputAccordingType() {
        super.outputAccordingType()
        println("View the file $id $content $extension")
    }
}