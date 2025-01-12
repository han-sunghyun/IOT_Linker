package com.developsunghyun.iot_linker.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WidgetViewModel() : ViewModel() {

    //===========위젯 파라미터
    private val _setStrData1 = MutableStateFlow<String?>(null)
    val setStrData1: StateFlow<String?> = _setStrData1

    private val _setStrData2 = MutableStateFlow<String?>(null)
    val setStrData2: StateFlow<String?> = _setStrData2

    private val _setStrData3 = MutableStateFlow<String>("")
    val setStrData3: StateFlow<String> = _setStrData3

    private val _setStrData4 = MutableStateFlow<String>("")
    val setStrData4: StateFlow<String> = _setStrData4


    fun setStrData1(str: String){
        _setStrData1.value = str
    }

    fun setStrData2(str: String){
        _setStrData2.value = str
    }

    //==========데이터 송신
    private val _writeData1Enable = MutableStateFlow<String>("true")
    val writeData1Enable: StateFlow<String> = _writeData1Enable
    private val _writeData1 = MutableStateFlow<String?>(null)
    val writeData1: StateFlow<String?> = _writeData1

    private val _writeData2Enable = MutableStateFlow<String>("true")
    val writeData2Enable: StateFlow<String> = _writeData2Enable
    private val _writeData2 = MutableStateFlow<String?>(null)
    val writeData2: StateFlow<String?> = _writeData2


    fun writeData1Enable(state: String){
        _writeData1Enable.value = state
    }
    fun writeData1(str: String){
        _writeData1.value = str
    }

    fun writeData2Enable(state: String){
        _writeData2Enable.value = state
    }
    fun writeData2(str: String){
        _writeData2.value = str
    }


    //==========데이터 수신
    private val _readData1 = MutableStateFlow<String?>(null)
    val readData1: StateFlow<String?> = _readData1

    private val _readData2 = MutableStateFlow<String?>(null)
    val readData2: StateFlow<String?> = _readData2

    fun readData1(str: String){
        _readData1.value = str
    }

    fun readData2(str: String){
        _readData2.value = str
    }

}