package com.example.neosoftassesment.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.neosoftassesment.R
import com.example.neosoftassesment.databinding.FragmentRegisterBinding
import com.example.neosoftassesment.viewModel.RegisterViewModel
import com.github.dhaval2404.imagepicker.ImagePicker


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var registerViewModel: RegisterViewModel
    private var isUserInputValid = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.nextBtn.setOnClickListener {
            validateInput()
        }

        binding.cameraBtn.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
    }

    private fun validateInput() {

        val firstNameValidation = registerViewModel.validFirstName(binding.etFirstname.text.toString())
        if(firstNameValidation == null){
            binding.etFirstnameContainer.helperText = null
        } else {
            binding.etFirstnameContainer.helperText = firstNameValidation
            isUserInputValid = false
        }

        val lastNameValidation = registerViewModel.validLastName(binding.etLastname.text.toString())
        if (lastNameValidation == null) {
            binding.etLastnameContainer.helperText = null

        } else {
            binding.etLastnameContainer.helperText = lastNameValidation
            isUserInputValid = false
        }

        val validPhoneNumber = registerViewModel.validPhone(binding.etPhone.text.toString())
        if(validPhoneNumber == null){
            binding.etPhoneContainer.helperText = null
        }else{
            binding.etPhoneContainer.helperText = validPhoneNumber
            isUserInputValid = false
        }

        val emailValidation = registerViewModel.validEmail(binding.etEmail.text.toString())
        if(emailValidation == null){
            binding.etEmailContainer.helperText = null
        }else{
            binding.etEmailContainer.helperText = emailValidation
        }


        val validPassword = registerViewModel.validPassword(binding.etPassword.text.toString())
        if (validPassword == null){
            binding.etPasswordContainer.helperText = null
        }else{
            binding.etPasswordContainer.helperText = validPassword
            isUserInputValid = false
        }

        val validConfirmPassword = registerViewModel.validcnfmPassword(binding.etConfirmPassword.text.toString(), binding.etPassword.text.toString())
        if (validConfirmPassword == null){
            binding.etConfirmPasswordContainer.helperText = null
        }else{
            binding.etConfirmPasswordContainer.helperText = validConfirmPassword
            isUserInputValid = false
        }
        if (isUserInputValid){
            findNavController().navigate(R.id.action_registerFragment_to_userInfoFragment)
        }
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            binding.ivProfile.setImageURI(data?.data)
        }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }

    }