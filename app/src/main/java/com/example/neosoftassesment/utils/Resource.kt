package com.example.neosoftassesment.utils

sealed class Resource<out T>(val status: Status, val dataEvent: Event<T>?) {

    data class Success<out T>(private val data: T? = null) :
        Resource<T>(Status.SUCCESS, data?.let { Event(it) })

    data class Error<out T>(private val data: T? = null) :
        Resource<T>(Status.ERROR, data?.let { Event(it) })

    data class Loading<out T>(private val data: T? = null) :
        Resource<T>(Status.LOADING, data?.let { Event(it) })

    data class Unknown<out T>(private val data: T? = null) :
        Resource<T>(Status.UNKNOWN, data?.let { Event(it) })

    fun peekData(): T? = dataEvent?.peekContent()
}