package com.peter.gooroo.process

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.peter.gooroo.data.PostTen
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

        val inputList = viewModel.intToArray(number).toMutableList()

        val adapter = ImageAdapter(inputList)

        binding.listView.adapter = adapter

        for (i in viewModel.intToArray(number)){
            viewModel.getValue(i.integer)
        }

        var count = 0
        val tenValue = mutableListOf<Double>()


        viewModel.receiveValue.observe(viewLifecycleOwner, Observer { it ->

            inputList[it.integer-1] = it
            val newList = inputList.sortedByDescending { process ->
                process.processedValue
            }

            Log.d("ProcessFragment","the value of = $newList")

            binding.listView.adapter = ImageAdapter(newList)

            count ++

            if (count == inputList.size && inputList.size >= 10){
                for (i in 0..9){
                    tenValue.add(newList[i].processedValue)
                }
                Log.d("ProcessFragment","the value of 10 number = $tenValue")
                viewModel.postTenValue(PostTen(tenValue))
            }

        })

        viewModel.combineResult.observe(viewLifecycleOwner, Observer {
            val alert = AlertDialog.Builder(this.context)
            alert.setMessage(it)
            alert.setTitle("The Combined Result")
            alert.show()
        })


        return binding.root
    }
}