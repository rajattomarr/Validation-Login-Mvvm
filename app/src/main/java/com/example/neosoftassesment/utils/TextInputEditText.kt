package com.example.neosoftassesment.utils

import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.setTextOnChange(value: String) {
    if (text.toString() != value) {
        setText(value)
    }
}