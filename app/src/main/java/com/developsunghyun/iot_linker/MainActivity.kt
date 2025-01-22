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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.developsunghyun.iot_linker.Model.Repository.BluetoothControl
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.PermissionManager
import com.developsunghyun.iot_linker.View.Screen.BluetoothControlViewModelFactory
import com.developsunghyun.iot_linker.View.Screen.HomeScreen
import com.developsunghyun.iot_linker.View.Screen.LayoutCompositionView
import com.developsunghyun.iot_linker.View.Screen.LayoutSelect
import com.developsunghyun.iot_linker.View.Screen.WidgetView
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.ButtonWidgetScreen
import com.developsunghyun.iot_linker.View.Widget.SwitchWidgetScreen
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
                var selectIndex by remember { mutableIntStateOf(0) }
                val navController = rememberNavController()
                val dbManager = LocalDataRepository(applicationContext)

                val bluetoothViewModel: BluetoothControlViewModel = viewModel(factory = BluetoothControlViewModelFactory(this))

//                val coroutineScope = rememberCoroutineScope()

                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

//                if(windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT){
//                    Log.d("LOG", "${windowSizeClass.windowHeightSizeClass} | ${windowSizeClass.windowWidthSizeClass}")
//                }
                val widgetViewList = dbManager.getListData().value
                widgetViewList?.get(0)?.let { Log.d("TEST", it.name) }

                NavHost(
                    navController = navController,
                    startDestination = "homeScreen",
                ) {
                    composable("HomeScreen") { HomeScreen(navController, applicationContext, bluetoothViewModel, database = dbManager, selectIndex = selectIndex){selectIndex = it} }
                    composable("LayoutSelect") { LayoutSelect(navController = navController) }
                    composable("LayoutCompositionView") { LayoutCompositionView(navController = navController, bluetoothViewModel = bluetoothViewModel, database = dbManager) }

                    composable("ButtonWidgetScreen") { ButtonWidgetScreen(bluetoothViewModel = bluetoothViewModel, database = dbManager) }
                    composable("SwitchWidgetScreen") { SwitchWidgetScreen(bluetoothViewModel = bluetoothViewModel, database = dbManager) }

                    //WidgetView
                    composable("WidgetView/{intValue}",
                        arguments = listOf(navArgument("intValue") {
                            type = NavType.IntType // 정수형 타입으로 설정
                        }))
                    {
                        backStackEntry ->
                        val intValue = backStackEntry.arguments?.getInt("intValue")
                        WidgetView(bluetoothViewModel = bluetoothViewModel, database = dbManager, intValue = intValue!!)
                    }
                }

            }
        }
    }
}