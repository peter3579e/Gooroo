package com.peter.gooroo.process

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.peter.gooroo.GoorooApplication
import com.peter.gooroo.R
import com.peter.gooroo.data.PostTen

import com.peter.gooroo.databinding.ProcessFragmentBinding
import com.peter.gooroo.ext.getVmFactory

class ProcessFragment : Fragment() {

    private lateinit var binding: ProcessFragmentBinding

    /**
     * Lazily initialize our [ProcessViewModel].
     */

    private val viewModel by viewModels<ProcessViewModel> { getVmFactory(ProcessFragmentArgs.fromBundle(requireArguments()).inputNumber) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = ProcessFragmentBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        /**
         * Convert input number to list
         */

        val inputList = viewModel.intToList(viewModel.inputNumber.value!!).toMutableList()

        val adapter = ImageAdapter(inputList)

        binding.listView.adapter = adapter

        /**
         * Send the request one by one to server to get processed number
         */

        for (i in viewModel.intToList(viewModel.inputNumber.value!!)) {
            viewModel.getValue(i.integer)
        }

        var count = 0
        val tenValue = mutableListOf<Double>()

        /**
         * Receive the processed number and sort it by descending
         * Finally send the first ten number to server to get the combined string
         */
        viewModel.receiveValue.observe(viewLifecycleOwner, Observer { it ->

            inputList[it.integer - 1] = it
            val newList = inputList.sortedByDescending { process ->
                process.processedValue
            }

            Log.d("ProcessFragment", "the value of = $newList")

            binding.listView.adapter = ImageAdapter(newList)

            count++

            if (count == inputList.size && inputList.size >= 10) {
                for (i in 0..9) {
                    tenValue.add(newList[i].processedValue)
                }
                Log.d("ProcessFragment", "the value of 10 number = $tenValue")
                viewModel.postTenValue(PostTen(tenValue))
            }

        })

        /**
         * Receive the combined number and show it via alert dialog
         */

        viewModel.combineResult.observe(viewLifecycleOwner, Observer {
            val alert = AlertDialog.Builder(this.context)
            alert.setMessage(it)
            alert.setTitle(GoorooApplication.instance.getString(R.string.the_combined_result))
            alert.show()
        })


        return binding.root
    }
}