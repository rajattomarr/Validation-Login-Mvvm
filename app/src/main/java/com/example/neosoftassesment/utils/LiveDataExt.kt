package com.example.neosoftassesment.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnconsumedContent: (data: T) -> Unit
) {
    observe(owner) { event: Event<T>? ->
        event?.getContentIfNotConsumed()?.let(onEventUnconsumedContent)
    }
}


inline fun <T> LiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    crossinline onEventAction: (status: Status, data: T?) -> Unit
) {
    observe(owner) { resourceWrapper: Resource<T> ->
        onEventAction(resourceWrapper.status, resourceWrapper.peekData())
    }
}

inline fun <T> LiveData<Resource<T>>.observeResourceEvent(
    owner: LifecycleOwner,
    crossinline onEventUnconsumedContent: (status: Status, data: T) -> Unit
) {
    observe(owner) { resourceWrapper: Resource<T> ->
        resourceWrapper.dataEvent?.getContentIfNotConsumed()?.let { data: T ->
            onEventUnconsumedContent(resourceWrapper.status, data)
        }
    }
}

inline fun <T> LiveData<T?>.observeNonNull(
    owner: LifecycleOwner,
    crossinline action: (data: T) -> Unit
) {
    observe(owner) {
        it?.let(action)
    }
}