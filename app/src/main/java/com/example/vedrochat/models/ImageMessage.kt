package com.example.vedrochat.models

import com.example.vedrochat.extensions.humanizeDiff
import java.util.Date

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var imageUrl: String
): BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String = "id: $id ${from?.firstName} " +
            "${if (isIncoming) "получил" else "отправил"} изображение: $imageUrl ${date.humanizeDiff()}"
}