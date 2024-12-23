package com.developsunghyun.iot_linker.ViewModel

import android.bluetooth.BluetoothSocket
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developsunghyun.iot_linker.Model.Data.BluetoothDeviceData
import com.developsunghyun.iot_linker.Model.Repository.BluetoothControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BluetoothControlViewModel(private val bluetoothControl: BluetoothControl) : ViewModel() {
    private val _isBluetoothEnabled = MutableStateFlow(false)
    val isBluetoothEnabled: StateFlow<Boolean> = _isBluetoothEnabled

    private val _pairedDevices = MutableStateFlow<List<BluetoothDeviceData>>(emptyList())
    val pairedDevices: StateFlow<List<BluetoothDeviceData>> = _pairedDevices

    private val _deviceData = MutableStateFlow<BluetoothDeviceData>(
        BluetoothDeviceData("", "", null, null))
    val deviceData: StateFlow<BluetoothDeviceData> get() = _deviceData

    // 블루투스 상태 업데이트
    fun updateBluetoothStatus() {
        _isBluetoothEnabled.value = bluetoothControl.checkBluetoothEnabled()
    }

    // 페어링된 기기 검색
    fun searchPairedDevices() {
        viewModelScope.launch {
            _pairedDevices.value = bluetoothControl.pairedDevicesSearch()
        }
    }

    // 블루투스 기기 연결
    fun connectToDevice(address: String) {
        viewModelScope.launch {
            val socket = withContext(Dispatchers.IO) {
                bluetoothControl.connect(address) // Bluetooth 연결 작업을 IO 스레드에서 실행
            }
//            if (socket != null && socket.isConnected) {
//
//            } else {
//
//            }
        }
    }

    fun setDeviceData(name: String, address: String){
        _deviceData.value = BluetoothDeviceData(name, address, null, null)

        Log.d("LOG", deviceData.value.deviceName)
    }

    // 블루투스 기기 연결 해제
    fun disconnectDevice(socket: BluetoothSocket?) {
        bluetoothControl.disconnect(socket)
    }
}