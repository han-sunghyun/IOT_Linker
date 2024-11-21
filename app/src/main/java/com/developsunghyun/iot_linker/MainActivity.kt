package com.developsunghyun.iot_linker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developsunghyun.iot_linker.View.Screen.HomeScreen
import com.developsunghyun.iot_linker.View.Screen.LayoutCompositionView
import com.developsunghyun.iot_linker.View.Screen.LayoutSelect
import com.developsunghyun.iot_linker.ViewModel.CreateViewModel
import com.developsunghyun.iot_linker.ui.theme.IOT_LinkerTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IOT_LinkerTheme() {
//                val coroutineScope = rememberCoroutineScope()
////                HomeScreen()
//                Layout_2Slot_1(module1 = { SwitchWidget(labelStrList = "Button1,a,b,c", stateSetStrList = "false,true,false,false")},
//                    module2 = {ButtonWidget(labelStrList = "a,b,c", positionStrList = "0,1,2,1")})
//
//                val dbManager = LocalDataRepository(applicationContext)
//
//                coroutineScope.launch {
//                    dbManager.testInsert()
//                }

                val navController = rememberNavController()

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IOT_LinkerTheme {
        Greeting("Android")
    }
}