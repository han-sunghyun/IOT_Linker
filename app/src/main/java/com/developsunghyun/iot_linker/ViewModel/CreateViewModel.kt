package com.developsunghyun.iot_linker.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.developsunghyun.iot_linker.Model.Data.LayoutItem

class CreateViewModel : ViewModel() {
    private val _layoutData = mutableStateOf(LayoutItem()) // 초기값 설정
    val layoutData: State<LayoutItem> get() = _layoutData

    fun setLayoutType(layoutType: String) {
        _layoutData.value = _layoutData.value.copy(layoutType = layoutType)
    }
    fun setSlitNumber(number: Int){
        _layoutData.value = _layoutData.value.copy(slotNumber = number)
    }
    fun setModuleArray(num: Int, module: String) {
        when(num){
            0 -> _layoutData.value = _layoutData.value.copy(module0 = module)
            1 -> _layoutData.value = _layoutData.value.copy(module1 = module)
            2 -> _layoutData.value = _layoutData.value.copy(module2 = module)
            3 -> _layoutData.value = _layoutData.value.copy(module3 = module)
            4 -> _layoutData.value = _layoutData.value.copy(module4 = module)
            5 -> _layoutData.value = _layoutData.value.copy(module5 = module)
        }
    }
}