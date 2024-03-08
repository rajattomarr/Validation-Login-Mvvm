package com.example.neosoftassesment.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.neosoftassesment.R
import com.example.neosoftassesment.databinding.FragmentRegisterBinding
import com.example.neosoftassesment.utils.Status
import com.example.neosoftassesment.utils.observeResource
import com.example.neosoftassesment.utils.setErrorStatus
import com.example.neosoftassesment.utils.setTextOnChange
import com.example.neosoftassesment.viewModel.RegisterViewModel
import com.github.dhaval2404.imagepicker.ImagePicker


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var registerViewModel: RegisterViewModel
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
        setupView(savedInstanceState)
        setupObservers()
    }

     private fun setupView(savedInstanceState: Bundle?) {

        with(binding) {
            // Register text change listener on Email field for validations
            binding.etEmail.doOnTextChanged { text, _, _, _ ->
                // When email text changes, delegate to the LoginViewModel
                registerViewModel.onEmailChange(text.toString())
            }

            binding.etFirstname.doOnTextChanged{text, _, _, _ ->
                registerViewModel.onFirstNameChange(text.toString())

            }

            binding.etLastname.doOnTextChanged(){ text, _, _, _ ->
                registerViewModel.onLastNameChange(text.toString())
            }

            binding.etPassword.doOnTextChanged(){text, _, _, _ ->
                registerViewModel.onPasswordChange(text.toString())
            }

            binding.etPhone.doOnTextChanged{text, _, _, _ ->
                registerViewModel.onPhoneChange(text.toString())

            }
            binding.etConfirmPassword.doOnTextChanged{text, _, _, _ ->
                registerViewModel.onConfirmPasswordChange(text.toString())
            }
        }
    }
     private fun setupObservers() {
         binding.nextBtn.setOnClickListener {
         registerViewModel.onLogin()

        }
         registerViewModel.loginProgress.observe(viewLifecycleOwner){
             if(it){
                 registerViewModel.resetLoginProcess()
                 findNavController().navigate(R.id.action_registerFragment_to_userInfoFragment)
             }

         }

         //email
         registerViewModel.emailField.observe(viewLifecycleOwner) { emailValue ->
             binding.etEmail.setTextOnChange(emailValue)
         }

         registerViewModel.emailValidation.observeResource(this) { status: Status, messageResId: Int? ->
             binding.etEmailContainer.setErrorStatus(
                 status,
                 messageResId?.run { getString(this) }
             )
         }
         // first Name
         registerViewModel.firstNameField.observe(viewLifecycleOwner){firstNameValue ->
             binding.etFirstname.setTextOnChange(firstNameValue)
         }
         registerViewModel.firstNameValidation.observeResource(this) { status: Status, messageResId: Int? ->
             binding.etFirstnameContainer.setErrorStatus(
                 status,
                 messageResId?.run { getString(this) }
             )
         }

         //last name
         registerViewModel.lastNameField.observe(viewLifecycleOwner){firstNameValue ->
             binding.etLastname.setTextOnChange(firstNameValue)
         }
         registerViewModel.lastNameValidation.observeResource(this) { status: Status, messageResId: Int? ->
             binding.etLastnameContainer.setErrorStatus(
                 status,
                 messageResId?.run { getString(this) }
             )
         }

         //password
         registerViewModel.passwordField.observe(viewLifecycleOwner){passwordValue ->
             binding.etPassword.setTextOnChange(passwordValue)
         }
         registerViewModel.passwordValidation.observeResource(this) { status: Status, messageResId: Int? ->
             binding.etPasswordContainer.setErrorStatus(
                 status,
                 messageResId?.run { getString(this) }
             )
         }

         //confirm password
         registerViewModel.confirmPasswordField.observe(viewLifecycleOwner){confirmPasswordValue ->
             binding.etConfirmPassword.setTextOnChange(confirmPasswordValue)
         }
         registerViewModel.confirmPasswordValidation.observeResource(this) { status: Status, messageResId: Int? ->
             binding.etConfirmPasswordContainer.setErrorStatus(
                 status,
                 messageResId?.run { getString(this) }
             )
         }

         //confirm password
         registerViewModel.phoneField.observe(viewLifecycleOwner){phoneValue ->
             binding.etPhone.setTextOnChange(phoneValue)
         }
         registerViewModel.phoneValidation.observeResource(this) { status: Status, messageResId: Int? ->
             binding.etPhoneContainer.setErrorStatus(
                 status,
                 messageResId?.run { getString(this) }
             )
         }
         binding.cameraBtn.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
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