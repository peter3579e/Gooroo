package com.peter.gooroo.process

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.peter.gooroo.data.Process
import com.peter.gooroo.databinding.ItemNumberCellBinding

class ImageAdapter(private val data:List<Process>):BaseAdapter() {


    override fun getCount(): Int {
        Log.d("ImageAdapter","Run1")
        Log.d("ImageAdapter","${data.size}")
        return data.size
    }

    override fun getItem(position: Int): Any {
        Log.d("ImageAdapter","Run2")
        Log.d("ImageAdapter","${position}")
       return data[position]
    }

    override fun getItemId(position: Int): Long {
        Log.d("ImageAdapter","Run3")
        Log.d("ImageAdapter","${position}")
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemNumberCellBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        Log.d("ImageAdapter","Run4")
        binding.number = data[position]
        Log.d("ImageAdapter","Run5")
        return binding.root
    }
}