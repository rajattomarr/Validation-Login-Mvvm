package com.example.neosoftassesment.viewModel
import androidx.lifecycle.ViewModel


class AddressViewModel() : ViewModel() {
    val state = listOf("Delhi", "Goa", "Gujarat", "Bihar", "Assam", "Maharashtra")
    fun validAddressName(addressText : String) : String? {
        if(addressText.length < 3){
            return "Minimum 3 characters Required"
        }
        return null
    }

     fun validLandmark(landMarkText :String) : String? {
        if(landMarkText.length < 3){
            return "Minimum 3 characters Required"
        }
        return null
    }

     fun validPostCode(postCodeText : String) : String? {
        if(postCodeText.length < 6){
            return "Minimum 6 Number Required"
        }
        return null
    }
}