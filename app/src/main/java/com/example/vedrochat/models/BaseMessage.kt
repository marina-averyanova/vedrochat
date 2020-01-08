package com.example.vedrochat.models

import java.util.Date

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {

    abstract fun formatMessage(): String

    companion object AbstractFactory {
        private var lastId = 0;

        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: MessageType,
            payload: Any?,
            isIncoming: Boolean = false
        ): BaseMessage {
            lastId++

            return when (type) {
                MessageType.TEXT -> TextMessage(
                    id = "$lastId",
                    from = from,
                    chat = chat,
                    isIncoming = isIncoming,
                    date = date,
                    text = "$payload"
                )
                MessageType.IMAGE -> ImageMessage(
                    id = "$lastId",
                    from = from,
                    chat = chat,
                    isIncoming = isIncoming,
                    date = date,
                    imageUrl = "$payload"
                )
            }
        }
    }
}