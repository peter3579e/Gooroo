package com.peter.gooroo.process

import androidx.lifecycle.ViewModel
import com.peter.gooroo.data.Process

class ProcessViewModel :ViewModel(){

    fun intToArray( number : Int):List<Process>{
        val list = mutableListOf<Process>()
        for (i in 1..number){
            list.add(Process(i,0))
        }
        return list
    }
}