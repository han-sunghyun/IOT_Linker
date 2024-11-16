package com.developsunghyun.iot_linker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.developsunghyun.iot_linker.Model.Repository.DBManager
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_1
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.SwitchWidget
import com.developsunghyun.iot_linker.ui.theme.IOT_LinkerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IOT_LinkerTheme {
                val coroutineScope = rememberCoroutineScope()
//                HomeScreen()
                Layout_2Slot_1(module1 = { SwitchWidget(labelStrList = "Button1,a,b,c", stateSetStrList = "false,true,false,false")},
                    module2 = {ButtonWidget(labelStrList = "a,b,c", positionStrList = "0,1,2,1")})

                val dbManager = DBManager(applicationContext)

                coroutineScope.launch {
                    dbManager.testInsert()
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