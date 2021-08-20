package com.peter.gooroo.process

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process
import com.peter.gooroo.network.GoorooApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProcessViewModel :ViewModel(){

    private var _receiveValue = MutableLiveData<Process>()
    val receiveValue: MutableLiveData<Process>
        get() = _receiveValue

    private var _combineResult = MutableLiveData<String>()
    val combineResult: MutableLiveData<String>
        get() = _combineResult

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun intToArray( number : Int):List<Process>{
        val list = mutableListOf<Process>()
        for (i in 1..number){
            list.add(Process(i,0.0))
        }
        return list
    }

    fun getValue(number: Int) {
        coroutineScope.launch {

            try {
                // this will run on a thread managed by Retrofit
                val result =GoorooApi.retrofitService.getNumberValue(number)
                _receiveValue.value = result

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

    fun postTenValue(tenNumber: PostTen) {
        coroutineScope.launch {

            try {
                // this will run on a thread managed by Retrofit
                val result =GoorooApi.retrofitService.postTenNumber(tenNumber)
                _combineResult.value = result.combine_result
                Log.d("ProcessViewModel","the return combined result = $result")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

}