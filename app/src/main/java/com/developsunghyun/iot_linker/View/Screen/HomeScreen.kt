package com.developsunghyun.iot_linker.View.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.InterfaceScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.ModuleScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.NoneScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.ToolScreen


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, widthDp = 370, heightDp = 800)
@Composable
fun HomeScreen(){

    val bottomNavItemList: List<NavigationItem> =
        listOf(
            NavigationItem(
                icon = Icons.Filled.Home,
                label = "인터페이스"
            ),
            NavigationItem(
                icon = Icons.Filled.Home,
                label = "모듈"
            ),
            NavigationItem(
                icon = Icons.Filled.Home,
                label = "도구"
            ),
            NavigationItem(
                icon = Icons.Filled.Home,
                label = "프로 젝트"
            )
        )
    var selectIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
//                navigationIcon = {
//                    IconButton (onClick = { /* do something */ }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },
                title = {
                    Text(
                        "Medium Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }

                }
            )
        },
        content = {paddingValues -> // paddingValues 인수 추가
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ){
                when(selectIndex){
                    0 -> InterfaceScreen()
                    1 -> ModuleScreen()
                    2 -> ToolScreen()
                    3 -> NoneScreen()
                }
            }
        },
        bottomBar = {
            NavigationBar {
                bottomNavItemList.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = selectIndex == index,
                        icon = { Icon(imageVector = navigationItem.icon, "")},
                        label = { Text(navigationItem.label) },
                        onClick = {
                            selectIndex = index
                        }
                    )
                }

            }
        }
    )
}

class NavigationItem(
    val icon: ImageVector,
    val label: String
)