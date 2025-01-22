package com.developsunghyun.iot_linker.View.Screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.Model.Data.InterfaceLayoutData
import com.developsunghyun.iot_linker.Model.Data.LayoutDataList
import com.developsunghyun.iot_linker.Model.Data.widgetDataList
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.View.Components.ItemLayout2
import com.developsunghyun.iot_linker.ViewModel.CreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutSelect(
    navController : NavController,
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
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }

                }
            )
        },
        content = {
                paddingValues ->
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ){
                LayoutListView(navController)
            }
        }
    )
}

@Composable
fun LayoutListView(
    navController : NavController,
    viewModel: CreateViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
){
    for ((index,layoutList) in LayoutDataList.layoutListData.withIndex()){
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = (index + 2).toString()
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp)
        ) {
            items(layoutList) { layoutData -> // 데이터 객체를 변수명 'widgetData'로 사용
                LayoutImageView(
                    onClick = {
                        viewModel.upDataInterfaceLayoutType(layoutData.layoutType)
                        navController.navigate("LayoutCompositionView")
                    },
                    image = layoutData.image
                ) // ItemLayout2에 'widgetData' 전달
            }

        }
    }
}

@Composable
fun LayoutImageView(
    onClick: () -> Unit = {},
    image: Int = R.drawable.layout_2solt1,
    name: String = ""
){
    Surface(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Image(
            modifier = Modifier
                .clickable {onClick()} ,
            painter = painterResource(id = image),
            contentDescription = name,
//            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}
