package com.peter.gooroo.process

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import com.peter.gooroo.GoorooApplication
import com.peter.gooroo.R
import com.peter.gooroo.data.Process
import com.peter.gooroo.databinding.ItemNumberCellBinding

class ImageAdapter(private val data:List<Process>):BaseAdapter() {


    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
       return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemNumberCellBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        /**
         * indicate the first 10 cells is in different colors
         */
        if (position <= 9){
            binding.cardView.setCardBackgroundColor(GoorooApplication.instance.getColor(R.color.orange_8b572a))
        }

        binding.number = data[position]
        return binding.root
    }
}