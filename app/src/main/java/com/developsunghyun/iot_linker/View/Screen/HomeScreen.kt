package com.developsunghyun.iot_linker.View.Screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.Model.Data.BluetoothDeviceData
import com.developsunghyun.iot_linker.Model.Repository.BluetoothControl
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.View.Components.MinimalDialog
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.InterfaceScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.ModuleScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.NoneScreen
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.ToolScreen
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    context: Context,
    viewModel: BluetoothControlViewModel,
    database: LocalDataRepository,
) {
    // BluetoothControlViewModel 초기화 (viewModel() 사용)
    val bluetoothViewModel: BluetoothControlViewModel = viewModel

    val windowSizeClass = calculateWindowSizeClass(LocalContext.current as Activity)
    var selectIndex by remember { mutableIntStateOf(0) }

    // 공통 레이아웃 처리 (BottomNavigation 또는 SideNavigation)
    NavigationLayout(
        context = context,
        selectIndex = selectIndex,
        navController = navController,
        windowSizeClass = windowSizeClass,
        viewModel = bluetoothViewModel,
        onSelectIndexChanged = { newIndex -> selectIndex = newIndex },
        isSideNavigation = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact,
        database = database,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationLayout(
    context: Context,
    selectIndex: Int,
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    viewModel: BluetoothControlViewModel,
    onSelectIndexChanged: (Int) -> Unit,
    isSideNavigation: Boolean, // 사이드 네비게이션 여부
    database: LocalDataRepository,
) {
    val isBluetoothEnabled = viewModel.isBluetoothEnabled.collectAsState()
    val device = viewModel.deviceData.collectAsState()
    val connectState = viewModel.connectState.collectAsState()

    var dialogView by remember { mutableStateOf(false) }

    // 다이얼로그 처리
    if (dialogView) {
        viewModel.searchPairedDevices()
        MinimalDialog(viewModel) { dialogView = false }
    }


    if (isSideNavigation) {
        Row {
            // 사이드 네비게이션
            NavigationRail {
                Spacer(Modifier.padding(top = 70.dp))
                bottomNavItemList.forEachIndexed { index, navigationItem ->
                    NavigationRailItem(
                        selected = selectIndex == index,
                        icon = { Icon(imageVector = navigationItem.icon, "") },
                        label = { Text(navigationItem.label) },
                        onClick = { onSelectIndexChanged(index) }
                    )
                    Spacer(Modifier.padding(top = 10.dp))
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Medium Top App Bar", maxLines = 1, overflow = TextOverflow.Ellipsis)
                            },
                            actions = {
                                TextButton(onClick = { dialogView = true }) {
                                    Text(text = device.value.deviceName)
                                }
                                IconButton(onClick = { viewModel.connectToDevice(device.value.deviceAddress) }) {
                                    Icon(
                                        modifier = Modifier.padding(0.dp),
                                        painter =
                                        if(isBluetoothEnabled.value){
                                            painterResource(id = R.drawable.bluetooth_disabled)
                                        }else{
                                            if (connectState.value)
                                            {
                                                painterResource(id = R.drawable.bluetooth_connected)
                                            }else{
                                                painterResource(id = R.drawable.bluetooth)
                                            }
                                        },
                                        contentDescription = "Localized description")
                                }
                            }
                        )
                    },
                    content = { paddingValues ->
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)) {
                            when (selectIndex) {
                                0 -> InterfaceScreen(navController, windowSizeClass.widthSizeClass)
                                1 -> ModuleScreen(navController, windowSizeClass.widthSizeClass, database = database)
                                2 -> ToolScreen(navController, windowSizeClass.widthSizeClass)
                                3 -> NoneScreen()
                            }
                        }
                    }
                )
            }
        }
    } else {
        // 바텀 네비게이션
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("IOT Linker", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    actions = {
                        TextButton(onClick = { dialogView = true }) {
                            Text(text = device.value.deviceName)
                        }
                        IconButton(onClick =
                        {
                            viewModel.connectToDevice(device.value.deviceAddress)
                        }
                        )
                        {
                            Icon(
                                modifier = Modifier.padding(0.dp),
                                painter =
                                if(isBluetoothEnabled.value){
                                    painterResource(id = R.drawable.bluetooth_disabled)
                                }else{
                                    if (connectState.value)
                                    {
                                        painterResource(id = R.drawable.bluetooth_connected)
                                    }else{
                                        painterResource(id = R.drawable.bluetooth)
                                    }
                                },
                                contentDescription = "Localized description")
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                    when (selectIndex) {
                        0 -> InterfaceScreen(navController, windowSizeClass.widthSizeClass)
                        1 -> ModuleScreen(navController, windowSizeClass.widthSizeClass, database = database)
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
                            icon = { Icon(imageVector = navigationItem.icon, "") },
                            label = { Text(navigationItem.label) },
                            onClick = { onSelectIndexChanged(index) }
                        )
                    }
                }
            }
        )
    }
}

// BluetoothControlViewModel 생성에 필요한 팩토리 클래스
@Suppress("UNCHECKED_CAST")
class BluetoothControlViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BluetoothControlViewModel(BluetoothControl(context)) as T
    }
}

class NavigationItem(
    val icon: ImageVector,
    val label: String
)

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
