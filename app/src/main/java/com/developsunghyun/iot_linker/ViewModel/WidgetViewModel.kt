package com.developsunghyun.iot_linker.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WidgetViewModel() : ViewModel() {

    private val _setStrData1 = MutableStateFlow<String?>(null)
    val setStrData1: StateFlow<String?> = _setStrData1

    private val _setStrData2 = MutableStateFlow<String?>(null)
    val setStrData2: StateFlow<String?> = _setStrData2

    private val _setStrData3 = MutableStateFlow<String>("")
    val setStrData3: StateFlow<String> = _setStrData3

    private val _setStrData4 = MutableStateFlow<String>("")
    val setStrData4: StateFlow<String> = _setStrData4


    private val _sandData1Enable = MutableStateFlow<String>("true")
    val sandData1Enable: StateFlow<String> = _sandData1Enable
    private val _sandData1 = MutableStateFlow<String?>(null)
    val sandData1: StateFlow<String?> = _sandData1

    private val _sandData2Enable = MutableStateFlow<String>("true")
    val sandData2Enable: StateFlow<String> = _sandData2Enable
    private val _sandData2 = MutableStateFlow<String?>(null)
    val sandData2: StateFlow<String?> = _sandData2



    fun setStrData1(str: String){
        _setStrData1.value = str
    }

    fun setStrData2(str: String){
        _setStrData2.value = str
    }


    fun sandData1Enable(state: String){
        _sandData1Enable.value = state
    }
    fun sandData1(str: String){
        _sandData1.value = str
    }

    fun sandData2Enable(state: String){
        _sandData2Enable.value = state
    }
    fun sandData2(str: String){
        _sandData2.value = str
    }

}