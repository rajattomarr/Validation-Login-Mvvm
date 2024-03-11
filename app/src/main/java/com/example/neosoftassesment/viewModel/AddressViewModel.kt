package com.example.neosoftassesment.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neosoftassesment.utils.Resource
import com.example.neosoftassesment.utils.Status
import com.example.neosoftassesment.utils.Validation
import com.example.neosoftassesment.utils.Validator
import com.example.neosoftassesment.utils.filterValidation


class AddressViewModel() : ViewModel() {
    val addressField: MutableLiveData<String> = MutableLiveData()

    val landMarkField: MutableLiveData<String> = MutableLiveData()

    val postCodeField: MutableLiveData<String> = MutableLiveData()

    val loginProgress: MutableLiveData<Boolean> = MutableLiveData()

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()


    fun onAddressChange(address: String) = addressField.postValue(address)
    val addressValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.ADDRESS)

    fun onLandmarkChange(landmark: String) = landMarkField.postValue(landmark)
    val landmarkValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.LANDMARK)

    fun onPostcodeChange(postcode: String) = postCodeField.postValue(postcode)
    val postcodeValidation: LiveData<Resource<Int>> =
        validationsList.filterValidation(Validation.Field.POSTCODE)

    fun onLogin() {
        val addressValue: String? = addressField.value
        val landmarkValue: String? = landMarkField.value
        val postcodeValue: String? = postCodeField.value
        val validations = Validator.validateAddressFields(addressValue,landmarkValue,postcodeValue)
        validationsList.postValue(validations)

        if (addressValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }
        if (landmarkValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }

        if (postcodeValue != null
            && validations.isNotEmpty()
            && validations.count { validation -> validation.resource.status == Status.SUCCESS } == validations.size
        ) {
            loginProgress.postValue(true)
        }
    }

    val state = listOf("Delhi", "Goa", "Gujarat", "Bihar", "Assam", "Maharashtra")
}