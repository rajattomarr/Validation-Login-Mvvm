package com.example.neosoftassesment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.neosoftassesment.R
import com.example.neosoftassesment.databinding.FragmentUserInfoBinding
import com.example.neosoftassesment.utils.degree
import com.example.neosoftassesment.utils.designation
import com.example.neosoftassesment.utils.domain
import com.example.neosoftassesment.utils.yearofPassing
import com.example.neosoftassesment.viewModel.RegisterViewModel
import com.example.neosoftassesment.viewModel.UserInfoViewModel


class UserInfoFragment : Fragment() {


    private var _binding : FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    lateinit var userInfoViewModel: UserInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInfoViewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.nextBtn.setOnClickListener{
            findNavController().navigate(R.id.action_userInfoFragment_to_addressFragment)
        }
        binding.previousBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val degreeAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_listitem,degree)
        binding.education.setAdapter(degreeAdapter)
        binding.education.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)

        }


        val yopAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_listitem,yearofPassing)
        binding.yearofpassing.setAdapter(yopAdapter)
        binding.yearofpassing.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)

        }

        val designationAdapter = ArrayAdapter(requireContext(),
            R.layout.dropdown_listitem, designation)
        binding.designation.setAdapter(designationAdapter)
        binding.designation.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)

        }

        val domainAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_listitem,domain)
        binding.domain.setAdapter(domainAdapter)
        binding.domain.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}