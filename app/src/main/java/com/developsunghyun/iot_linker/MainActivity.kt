package com.developsunghyun.iot_linker

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developsunghyun.iot_linker.Model.Repository.BluetoothControl
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.PermissionManager
import com.developsunghyun.iot_linker.View.Screen.BluetoothControlViewModelFactory
import com.developsunghyun.iot_linker.View.Screen.HomeScreen
import com.developsunghyun.iot_linker.View.Screen.LayoutCompositionView
import com.developsunghyun.iot_linker.View.Screen.LayoutSelect
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.ButtonWidgetScreen
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel
import com.developsunghyun.iot_linker.ui.theme.IOT_LinkerTheme
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("CoroutineCreationDuringComposition")
    @ExperimentalMaterial3AdaptiveApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            IOT_LinkerTheme() {

                val navController = rememberNavController()
                val dbManager = LocalDataRepository(applicationContext)

                val bluetoothViewModel: BluetoothControlViewModel = viewModel(factory = BluetoothControlViewModelFactory(this))

//                val coroutineScope = rememberCoroutineScope()

                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

//                if(windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT){
//                    Log.d("LOG", "${windowSizeClass.windowHeightSizeClass} | ${windowSizeClass.windowWidthSizeClass}")
//                }


                NavHost(
                    navController = navController,
                    startDestination = "homeScreen",
                ) {
                    composable("HomeScreen") { HomeScreen(navController, applicationContext, bluetoothViewModel) }
                    composable("LayoutSelect") { LayoutSelect(navController = navController) }
                    composable("LayoutCompositionView") { LayoutCompositionView(navController = navController) }

                    composable("ButtonWidgetScreen") { ButtonWidgetScreen(bluetoothViewModel) }
                }

            }
        }
    }
}