package com.example.neosoftassesment.utils

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.neosoftassesment.R
import java.util.regex.Pattern

object Validator {


    fun validateLoginFields(email: String?,firstName : String?,lastName : String?, password : String?,phone :String? , confirmPassword : String?): List<Validation> =
        mutableListOf<Validation>().apply {
            validateEmail(email)
            validateFirstName(firstName)
            validateLastName(lastName)
            validatePassword(password)
            validateConfirmPassword(confirmPassword,password)
            validatePhone(phone)

        }



    private fun MutableList<Validation>.validateFirstName(firstName: String?) {
        when {
            firstName.isNullOrBlank() ->
                add(Validation(Validation.Field.FIRSTNAME, Resource.Error(R.string.error_sign_up_name_field_empty)))
            firstName?.length!! < 3 ->
                add(Validation(Validation.Field.FIRSTNAME, Resource.Error(R.string.length_of_first_name)))
            else ->
                add(Validation(Validation.Field.FIRSTNAME, Resource.Success()))
        }
    }

    private fun MutableList<Validation>.validateLastName(lastName: String?) {
        when {
            lastName.isNullOrBlank() ->
                add(Validation(Validation.Field.LASTNAME, Resource.Error(R.string.last_name_empty)))
            lastName?.length!! < 3 ->
                add(Validation(Validation.Field.LASTNAME, Resource.Error(R.string.length_of_last_name)))
            else ->
                add(Validation(Validation.Field.LASTNAME, Resource.Success()))
        }
    }


    private fun MutableList<Validation>.validateConfirmPassword(confirmPassword: String?,password: String? ) {
        when {
            confirmPassword.isNullOrBlank() ->
                add(Validation(Validation.Field.CONFIRMPASSWORD, Resource.Error(R.string.cofirmPassword_empty)))
            confirmPassword != password ->
                add(Validation(Validation.Field.CONFIRMPASSWORD, Resource.Error(R.string.confirmPassword_doesnt_match)))
            else ->
                add(Validation(Validation.Field.CONFIRMPASSWORD, Resource.Success()))
        }
    }


    private fun MutableList<Validation>.validatePassword(password: String?) {
        when {
            password.isNullOrBlank() ->
                add(Validation(Validation.Field.PASSWORD, Resource.Error(R.string.password_empty)))

            password?.length!! < 8 ->
                add(Validation(Validation.Field.PASSWORD, Resource.Error(R.string.length_of_password)))

            !password.matches(".*[A-Z].*".toRegex()) ->
                add(Validation(Validation.Field.PASSWORD, Resource.Error(R.string.password_uppercase)))

            !password.matches(".*[a-z].*".toRegex()) ->
                add(Validation(Validation.Field.PASSWORD, Resource.Error(R.string.password_lowercase)))

            !password.matches(".*[@#\$%^&+=].*".toRegex())->
                add(Validation(Validation.Field.PASSWORD, Resource.Error(R.string.password_character)))
            else ->
                add(Validation(Validation.Field.PASSWORD, Resource.Success()))
        }
    }

    private fun MutableList<Validation>.validatePhone(phone: String?) {
        when {
            // When name is empty or blank, build a Validation with an appropriate error message
            phone.isNullOrBlank() ->
                add(Validation(Validation.Field.PHONE, Resource.Error(R.string.phone_not_null)))
            // When name is valid, build a Validation with success resource
            !phone.matches(".*[0-9].*".toRegex())->
                add(Validation(Validation.Field.PHONE, Resource.Error(R.string.must_be_10_digit)))
            phone.length != 10 ->
                add(Validation(Validation.Field.PHONE, Resource.Error(R.string.mustbedigit)))
            else ->
                add(Validation(Validation.Field.PHONE, Resource.Success()))
        }
    }



    private fun MutableList<Validation>.validateEmail(email: String?) {
        when {
            email.isNullOrBlank() ->
                add(
                    Validation(
                        Validation.Field.EMAIL,
                        Resource.Error(R.string.error_login_sign_up_email_field_empty)
                    )
                )
            !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() ->
                add(
                    Validation(
                        Validation.Field.EMAIL,
                        Resource.Error(R.string.error_login_sign_up_email_field_invalid)
                    )
                )
            else ->
                add(Validation(Validation.Field.EMAIL, Resource.Success()))
        }
    }

}

data class Validation(val field: Field, val resource: Resource<Int>) {


    enum class Field {
        EMAIL,
        FIRSTNAME,
        LASTNAME,
        PASSWORD,
        PHONE,
        CONFIRMPASSWORD
    }

}

fun LiveData<List<Validation>>.filterValidation(field: com.example.neosoftassesment.utils.Validation.Field): LiveData<Resource<Int>> =
    this.map { validations ->
        validations.find { validation -> validation.field == field }
            ?.resource
            ?: Resource.Unknown()
    }