package com.example.neosoftassesment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.neosoftassesment.R
import com.example.neosoftassesment.databinding.FragmentAddressBinding
import com.example.neosoftassesment.databinding.FragmentRegisterBinding
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
        initViews()
    }

    private fun initViews() {
        val stateAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_listitem,addressViewModel.state)
        binding.state.setAdapter(stateAdapter)
        binding.state.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val itemSelected = parent.getItemAtPosition(position)

            }
        binding.submitBtn.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val validAddress = addressViewModel.validAddressName(binding.address.text.toString())
        if(validAddress== null){
            binding.addressContainer.helperText = null
        } else{
            binding.addressContainer.helperText = validAddress
            isUserInputValid = false
        }

        val validLandMark = addressViewModel.validLandmark(binding.landmark.text.toString())
        if(validLandMark == null){
            binding.landmarkContainer.helperText = null
        } else{
            binding.landmarkContainer.helperText = validLandMark
            isUserInputValid = false
        }

        val validPostCode = addressViewModel.validPostCode(binding.postcode.text.toString())
        if (validPostCode == null){
            binding.postcodeContainer.helperText = null
        }else{
            binding.postcodeContainer.helperText = validPostCode
            isUserInputValid = false
        }
        if (isUserInputValid){
            Toast.makeText(context,"Sucess",Toast.LENGTH_SHORT).show();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}