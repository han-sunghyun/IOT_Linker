package com.developsunghyun.iot_linker.View.Screen

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.InterfaceScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.ModuleScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.NoneScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.ToolScreen


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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController : NavController,
){

    val windowSizeClass = calculateWindowSizeClass(LocalContext.current as Activity)
    var selectIndex by remember { mutableIntStateOf(0) }
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> BottomNavigationLayout(
            selectIndex = selectIndex, navController, windowSizeClass){ newIndex ->
            selectIndex = newIndex
        }
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> SideNavigationLayout(
            selectIndex = selectIndex, navController, windowSizeClass){ newIndex ->
            selectIndex = newIndex
        }
        else -> BottomNavigationLayout(
            selectIndex = selectIndex, navController, windowSizeClass){ newIndex ->
            selectIndex = newIndex
        } // 기본값 처리
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationLayout(
    selectIndex: Int,
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    onSelectIndexChanged: (Int) -> Unit,
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "IOT Linker",
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

                }
            )
        },
        content = {paddingValues -> // paddingValues 인수 추가
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ){
                when(selectIndex){
                    0 -> InterfaceScreen(navController, windowSizeClass.widthSizeClass)
                    1 -> ModuleScreen(navController, windowSizeClass.widthSizeClass)
                    2 -> ToolScreen(navController, windowSizeClass.widthSizeClass)
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
                            onSelectIndexChanged(index)
                        }
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideNavigationLayout(
    selectIndex: Int,
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    onSelectIndexChanged: (Int) -> Unit
){
    Row {
        // 사이드 네비게이션 바
        NavigationRail {
            Spacer(Modifier.padding(top = 70.dp))
            bottomNavItemList.forEachIndexed { index, navigationItem ->
                NavigationRailItem(
                    selected = selectIndex == index,
                    icon = { Icon(imageVector = navigationItem.icon, "") },
                    label = { Text(navigationItem.label) },
                    onClick = {
                        onSelectIndexChanged(index)
                    }
                )
                Spacer(Modifier.padding(top = 10.dp))
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Scaffold(
                topBar = {
                    TopAppBar(
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
                            0 -> InterfaceScreen(navController, windowSizeClass.widthSizeClass)
                            1 -> ModuleScreen(navController, windowSizeClass.widthSizeClass)
                            2 -> ToolScreen(navController, windowSizeClass.widthSizeClass)
                            3 -> NoneScreen()
                        }
                    }
                }
            )
        }
    }
}

class NavigationItem(
    val icon: ImageVector,
    val label: String
)