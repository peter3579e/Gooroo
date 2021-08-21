package com.peter.gooroo.process

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.gooroo.R
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process
import com.peter.gooroo.data.Result
import com.peter.gooroo.data.source.GoorooRepository
import com.peter.gooroo.network.GoorooApi
import com.peter.gooroo.util.Util.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProcessViewModel(private val goorooRepository: GoorooRepository, private val number: Int) :ViewModel(){

    private var _receiveValue = MutableLiveData<Process>()
    val receiveValue: MutableLiveData<Process>
        get() = _receiveValue

    private val _inputNumber = MutableLiveData<Int>().apply {
        number.let {
            value = it
        }
    }

    val inputNumber: LiveData<Int>
        get() = _inputNumber

    private var _combineResult = MutableLiveData<String>()
    val combineResult: MutableLiveData<String>
        get() = _combineResult

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun intToList(number : Int):List<Process>{
        val list = mutableListOf<Process>()
        for (i in 1..number){
            list.add(Process(i,0.0))
        }
        return list
    }

    /**
     * track [GoorooRepository.getNumberValue]: -> [DefaultGoorooRepository] : [GoorooRepository] -> [GoorooRemoteDataSource] : [GoorooDataSource]
     * @param number: the input number
     */

    fun getValue(number: Int) {
            coroutineScope.launch {
                val result = goorooRepository.getNumberValue(number)

                _receiveValue.value = when (result) {
                    is Result.Success -> {
                        result.data
                    }

                    else -> {
                        null
                    }
                }
            }
    }

    /**
     * track [GoorooRepository.getNumberValue]: -> [DefaultGoorooRepository] : [GoorooRepository] -> [GoorooRemoteDataSource] : [GoorooDataSource]
     * @param tenNumber: The first ten number will be taken on the body of http request
     */

    fun postTenValue(tenNumber: PostTen) {
        coroutineScope.launch {
            val result = goorooRepository.postTenNumber(tenNumber)

            _combineResult.value = when (result) {
                is Result.Success -> {
                    result.data.combine_result
                }

                else -> {
                    null
                }
            }
        }
    }

}