package com.example.neosoftassesment.utils

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorStatus(status: Status, errorMessage: String?) {
    when (status) {
        Status.ERROR -> error = errorMessage
        else -> isErrorEnabled = false
    }
}