package com.example.neosoftassesment.viewModel

import androidx.lifecycle.ViewModel
import com.example.neosoftassesment.utils.degree
import com.example.neosoftassesment.utils.designation
import com.example.neosoftassesment.utils.domain
import com.example.neosoftassesment.utils.yearofPassing

class UserInfoViewModel : ViewModel() {
    fun dropDownList(){
        degree
        yearofPassing
        designation
        domain
    }
    }



