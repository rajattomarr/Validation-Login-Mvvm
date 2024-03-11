package com.example.neosoftassesment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.neosoftassesment.R
import com.example.neosoftassesment.databinding.FragmentAddressBinding
import com.example.neosoftassesment.databinding.FragmentRegisterBinding
import com.example.neosoftassesment.utils.Status
import com.example.neosoftassesment.utils.observeResource
import com.example.neosoftassesment.utils.setErrorStatus
import com.example.neosoftassesment.utils.setTextOnChange
import com.example.neosoftassesment.viewModel.AddressViewModel
import com.example.neosoftassesment.viewModel.RegisterViewModel


class AddressFragment : Fragment() {

    private var _binding : FragmentAddressBinding? = null
    private val binding get() = _binding!!
    lateinit var addressViewModel: AddressViewModel
    private var isUserInputValid = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddressBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(savedInstanceState)
        setupObservers()

    }

    private fun setupObservers() {
        val stateAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_listitem,addressViewModel.state)
        binding.state.setAdapter(stateAdapter)
        binding.state.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val itemSelected = parent.getItemAtPosition(position)

            }
        binding.submitBtn.setOnClickListener {
            addressViewModel.onLogin()

        }

        addressViewModel.addressField.observe(viewLifecycleOwner) { addressValue ->
            binding.address.setTextOnChange(addressValue)
        }

        addressViewModel.addressValidation.observeResource(this) { status: Status, messageResId: Int? ->
            binding.addressContainer.setErrorStatus(
                status,
                messageResId?.run { getString(this) }
            )
        }

        addressViewModel.landMarkField.observe(viewLifecycleOwner) { landmarkValue ->
            binding.landmark.setTextOnChange(landmarkValue)
        }

        addressViewModel.landmarkValidation.observeResource(this) { status: Status, messageResId: Int? ->
            binding.landmarkContainer.setErrorStatus(
                status,
                messageResId?.run { getString(this) }
            )
        }

        addressViewModel.postCodeField.observe(viewLifecycleOwner) { postcodeValue ->
            binding.postcode.setTextOnChange(postcodeValue)
        }

        addressViewModel.postcodeValidation.observeResource(this) { status: Status, messageResId: Int? ->
            binding.postcodeContainer.setErrorStatus(
                status,
                messageResId?.run { getString(this) }
            )
        }
    }

    private fun setupView(savedInstanceState: Bundle?) {

        with(binding) {
            // Register text change listener on Email field for validations
            binding.address.doOnTextChanged { text, _, _, _ ->
                // When email text changes, delegate to the LoginViewModel
                addressViewModel.onAddressChange(text.toString())
            }

            binding.landmark.doOnTextChanged{text, _, _, _ ->
                addressViewModel.onLandmarkChange(text.toString())

            }

            binding.postcode.doOnTextChanged{text, _, _, _ ->
                addressViewModel.onPostcodeChange(text.toString())

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}