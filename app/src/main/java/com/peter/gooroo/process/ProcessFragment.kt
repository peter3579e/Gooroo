package com.peter.gooroo.process

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.peter.gooroo.data.Process

import com.peter.gooroo.databinding.ProcessFragmentBinding
import com.peter.gooroo.mainpage.MainPageViewModel

class ProcessFragment:Fragment() {

    private lateinit var binding: ProcessFragmentBinding

    private val viewModel: ProcessViewModel by lazy {
        ViewModelProvider(this).get(ProcessViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProcessFragmentBinding.inflate(inflater,container,false)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        val number = ProcessFragmentArgs.fromBundle(requireArguments()).inputNumber

        val adapter = ImageAdapter(viewModel.intToArray(number))

        binding.listView.adapter = adapter

        val list = mutableListOf<Process>()
        list.add(Process(1,1))

        binding.testButton.setOnClickListener {
            binding.listView.adapter = ImageAdapter(list)
        }


        Log.d("ProcessFragment", "the value from main page = $number")
        Log.d("ProcessFragment", "the array = ${viewModel.intToArray(number)}")

        return binding.root
    }
}