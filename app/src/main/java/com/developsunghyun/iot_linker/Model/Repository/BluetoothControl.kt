package com.developsunghyun.iot_linker.Model.Repository

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.developsunghyun.iot_linker.Model.Data.BluetoothDeviceData
import com.developsunghyun.iot_linker.View.PermissionManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID
import kotlin.concurrent.thread

const val MESSAGE_READ: Int = 0
const val MESSAGE_WRITE: Int = 1
const val MESSAGE_TOAST: Int = 2

class BluetoothControl(context: Context) {
    private val dataChannel = Channel<String>(Channel.BUFFERED)
    val receivedDataFlow = dataChannel.receiveAsFlow()

    private val myContext = context
    private val bluetoothManager: BluetoothManager = myContext.getSystemService(BluetoothManager::class.java)
    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

    private val sppUuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    //00001101-0000-1000-8000-00805F9B34FB
    //19B10000-E8F2-537E-4F6C-D104768A1214


    private val permission = PermissionManager(myContext)

    //블루투스 지원 확인
    fun checkBluetoothSupport(): Boolean{
        return bluetoothAdapter != null
    }

    //블루투스 활성화 상태 확인
    fun checkBluetoothEnabled(): Boolean{
        return bluetoothAdapter?.isEnabled ?: false
    }

    //패어링 된 기기 조회
    fun pairedDevicesSearch(): List<BluetoothDeviceData>{
        val deviceDataList: MutableList<BluetoothDeviceData> = mutableListOf()
        if(permission.checkPermission(Manifest.permission.BLUETOOTH_CONNECT)){

            val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->

                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address

                val deviceData = BluetoothDeviceData(deviceName = deviceName, deviceAddress = deviceHardwareAddress, deviceAlias = null, deviceType = null)
                deviceDataList.add(deviceData)
            }

        }

        return deviceDataList
    }

    fun connect(address: String): BluetoothSocket? {
        val device = bluetoothAdapter?.getRemoteDevice(address)
        val socket = device?.createRfcommSocketToServiceRecord(sppUuid)
        try {
            if(permission.checkPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
                socket?.connect()
            }else{
                return null
            }
            return socket
        } catch (e: IOException) {
            Log.e("BluetoothConnection", "연결 실패", e)
            try {
                socket?.close()
            } catch (closeException: IOException) {
                Log.e("BluetoothConnection", "소켓 닫기 실패", closeException)
            }
            return null
        }
    }

    fun disconnect(socket: BluetoothSocket?){
        socket?.close()
    }

    inner class IOStream(
        socket: BluetoothSocket?
    ) : Thread(){


        private val mmInStream: InputStream? = socket?.inputStream
        private val mmOutStream: OutputStream? = socket?.outputStream
        private val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream

        override fun run() {
            var numBytes: Int
            var receivedMessage: String

            while (true) {
                try {
                    numBytes = mmInStream!!.read(mmBuffer)
                    receivedMessage = String(mmBuffer, 0, numBytes)
                    // 데이터를 Channel에 전송
                    dataChannel.trySend(receivedMessage)
                    Log.d("TAG", "블루투스 데이터 입력")
                } catch (e: IOException) {
                    Log.d("TAG", "Input stream was disconnected", e)
                    break
                }
            }
        }

        fun write(num: String){
            try {
                mmOutStream?.write(num.toByteArray())
                Log.d("BluetoothStream", "데이터 전송 성공: $num")
            } catch (e: IOException) {
                Log.e("BluetoothStream", "데이터 전송 실패", e)
            }

        }

    }


    private val bluetoothStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothDevice.ACTION_ACL_DISCONNECTED == action) {
                val device: BluetoothDevice? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
                } else {
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                }
                if(permission.checkPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
                    Log.d("LOG", "Device disconnected: ${device?.name}")
                }
            }
        }
    }


}