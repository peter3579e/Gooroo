package com.peter.gooroo.mainpage

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.peter.gooroo.NavigationDirections
import com.peter.gooroo.databinding.MainpageFragmentBinding
import java.util.logging.Logger

class MainPageFragment:Fragment() {

    private lateinit var binding:MainpageFragmentBinding
    private val viewModel: MainPageViewModel by lazy {
        ViewModelProvider(this).get(MainPageViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MainpageFragmentBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.inputNumber.doOnTextChanged { text, start, before, count ->
            if (text.toString() == ""){
                viewModel.inputNumber.value = 0
            }else {
                viewModel.inputNumber.value = Integer.parseInt(text.toString())
            }
            Log.d("MainPageFragment","the input number ${viewModel.inputNumber.value}")
        }

        binding.sendButton.setOnClickListener {
            if (isFinished()){
                findNavController().navigate(NavigationDirections.navigateToProcessFragment(viewModel.inputNumber.value!!))
            }
        }



        return binding.root
    }

    private fun isFinished():Boolean{
        return when {
            viewModel.inputNumber.value != null && viewModel.inputNumber.value!! <=  100 && viewModel.inputNumber.value!! > 0-> {
                true
            }
            else -> {
                Log.d("MainPageFragment","run here")
                Toast.makeText(context,"Please enter a vaild number",Toast.LENGTH_SHORT).show()
                false
            }
        }
    }


}