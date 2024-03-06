package com.example.neosoftassesment.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

     fun validLastName(lastNameText:String) : String? {
        if(lastNameText.length < 3){
            return "Minimum 3 characters Name"
        }
        return null
    }

     fun validFirstName(firstNameText:String) : String? {
        if(firstNameText.length < 3){
            return "Minimum 3 characters Name"
        }
        return null
    }

    fun validEmail(emailText:String) : String? {
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return null
    }

     fun validPhone(phoneText :String) : String? {
        if(!phoneText.matches(".*[0-9].*".toRegex())){
            return "Must be Digit"
        }
        if (phoneText.length != 10){
            return "Must be 10 digits"
        }

        return null
    }

        fun validPassword(passwordText :String) : String? {
        if(passwordText.length < 8){
            return "Minimum 8 characters Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex())){
            return "Must Contain one Upper-Case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())){
            return "Must Contain one Lower-Case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex())){
            return "Must Contain one Special Character (@#\$%^&+=)"
        }
        return null
    }

     fun validcnfmPassword(cnfmPasswordText :String ,passwordText: String  ) : String? {
        if(cnfmPasswordText != passwordText){
            return "Password doesn't match"
        }
        return null
    }
}