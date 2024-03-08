package com.example.neosoftassesment.viewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neosoftassesment.utils.Resource
import com.example.neosoftassesment.utils.Status
import com.example.neosoftassesment.utils.Validation
import com.example.neosoftassesment.utils.Validator
import com.example.neosoftassesment.utils.filterValidation


class RegisterViewModel : ViewModel() {

    val emailField: MutableLiveData<String> = MutableLiveData()

    val firstNameField : MutableLiveData<String> = MutableLiveData()

    val lastNameField : MutableLiveData<String> = MutableLiveData()

    val passwordField : MutableLiveData<String> = MutableLiveData()

    val phoneField : MutableLiveData<String> = MutableLiveData()

    val confirmPasswordField : MutableLiveData<String> = MutableLiveData()


    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()

    val loginProgress: MutableLiveData<Boolean> = MutableLiveData()
    fun onEmailChange(email: String) = emailField.postValue(email)
    val emailValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.EMAIL)

    fun onFirstNameChange(firstName : String) = firstNameField.postValue(firstName)
    val firstNameValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.FIRSTNAME)

    fun onLastNameChange(lastName : String) = lastNameField.postValue(lastName)
    val lastNameValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.LASTNAME)

    fun onPasswordChange(password : String) = passwordField.postValue(password)
    val passwordValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.PASSWORD)

    fun onPhoneChange(phone : String) = phoneField.postValue(phone)
    val phoneValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.PHONE)

    fun onConfirmPasswordChange(confirmPassword : String) = confirmPasswordField.postValue(confirmPassword)
    val confirmPasswordValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.CONFIRMPASSWORD)

    fun resetLoginProcess(){
        loginProgress.postValue(false)
    }
    fun onLogin() {
        // Get the values of the fields
        val emailValue: String? = emailField.value
        val firstNameValue : String? = firstNameField.value
        val lastNameValue: String? = lastNameField.value
        val passwordValue: String? = passwordField.value
        val confirmPasswordValue: String? = confirmPasswordField.value
        val phoneValue: String? = phoneField.value


        val validations = Validator.validateLoginFields(emailValue,firstNameValue,lastNameValue,passwordValue,phoneValue,confirmPasswordValue)
        validationsList.postValue(validations)


        if (emailValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

        if (firstNameValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

        if (lastNameValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

        if (passwordValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

        if (confirmPasswordValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

        if (phoneValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

    }
}