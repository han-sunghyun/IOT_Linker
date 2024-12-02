package com.developsunghyun.iot_linker

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.Screen.HomeScreen
import com.developsunghyun.iot_linker.View.Screen.LayoutCompositionView
import com.developsunghyun.iot_linker.View.Screen.LayoutSelect
import com.developsunghyun.iot_linker.ui.theme.IOT_LinkerTheme

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

//                val coroutineScope = rememberCoroutineScope()

                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

//                if(windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT){
//                    Log.d("LOG", "${windowSizeClass.windowHeightSizeClass} | ${windowSizeClass.windowWidthSizeClass}")
//                }


                NavHost(
                    navController = navController,
                    startDestination = "homeScreen",
                ) {
                    composable("HomeScreen") { HomeScreen(navController) }
                    composable("LayoutSelect") { LayoutSelect(navController) }
                    composable("LayoutCompositionView") { LayoutCompositionView(navController = navController)}
                }

            }
        }
    }
}