package com.developsunghyun.iot_linker.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.developsunghyun.iot_linker.Model.Data.ModuleData
import com.developsunghyun.iot_linker.Model.Repository.ModuleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateViewModel : ViewModel() {
    private val repository = ModuleRepository()

    private val _interfaceName = MutableStateFlow<String>("")
    val interfaceName: StateFlow<String> = _interfaceName

    private val _interfaceLayoutType = MutableStateFlow<String>("")
    val interfaceLayoutType: StateFlow<String> = _interfaceLayoutType

    private val _modules = MutableStateFlow<List<ModuleData>>(List(6) { ModuleData() })
    val modules: StateFlow<List<ModuleData>> = _modules

    fun upDataInterfaceName(name: String){
        _interfaceName.value = name
    }
    fun upDataInterfaceLayoutType(layoutType: String){
        _interfaceLayoutType.value = layoutType
    }
    fun updateModule(index: Int, newModuleData: ModuleData) {
        _modules.value = _modules.value.toMutableList().apply {
            this[index] = newModuleData
        }
    }
}