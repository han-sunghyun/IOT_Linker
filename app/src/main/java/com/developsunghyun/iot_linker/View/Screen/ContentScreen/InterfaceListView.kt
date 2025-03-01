package com.developsunghyun.iot_linker.View.Screen.ContentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.View.Components.ItemLayout

//@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun InterfaceScreen(
    navController : NavController,
    windowSizeClass: WindowWidthSizeClass,
){
    Box(
        modifier = Modifier
            .padding(6.dp),
    ){
        Column (

        ){
            if(windowSizeClass == WindowWidthSizeClass.Compact){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                }
            }else{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomEnd,
        ) {
            FloatingActionButton(
                onClick = { navController.navigate("LayoutSelect") },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    }
}
