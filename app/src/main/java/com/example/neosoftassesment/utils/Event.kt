package com.example.neosoftassesment.utils

import java.util.concurrent.atomic.AtomicBoolean

data class Event<out T>(private val content: T) {

    private var isContentConsumed = AtomicBoolean(false)

    fun getContentIfNotConsumed(): T? = if (isContentConsumed.getAndSet(true)) {
        null
    } else {
        content
    }
    fun peekContent(): T = content

}
