package com.peter.gooroo.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainPageViewModel :ViewModel(){

    val inputNumber = MutableLiveData<Int>()

}