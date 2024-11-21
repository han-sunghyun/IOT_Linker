package com.developsunghyun.iot_linker.View.Screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.Model.Data.LayoutDataList
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.ViewModel.CreateViewModel

@Composable
fun LayoutSelect(
    navController : NavController,
    viewModel: CreateViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
){

    Surface(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(

        ) {
            SelectButton()


            for (layout in LayoutDataList.layoutListData){
                LayoutImageView(
                    onClick = {
                        viewModel.setLayoutType(layoutType = layout.layoutType)
                        viewModel.setSlitNumber(layout.slotNumber)

                        navController.navigate("LayoutCompositionView")
                    },
                    image = layout.image
                )
            }

        }
    }

}

@Preview
@Composable
fun SelectButton(
    button1: Boolean = true,
    button2: Boolean = false,
    button1Event : () -> Unit = {},
    button2Event : () -> Unit = {},
){
    Surface(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHighest
    ) {
        Row (
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp),
        ){
            Text(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable {
                        button1Event()
                    },
                text = "버튼1",
                fontSize = 18.sp,
                color = if(button1) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        button2Event()
                    },
                text = "버튼2",
                fontSize = 18.sp,
                color = if(button2) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
        }
    }
}

@Preview
@Composable
fun LayoutImageView(
    onClick: () -> Unit = {},
    image: Int = R.drawable.layout_2slot_1,
    name: String = ""
){
    Surface(
        shape = RoundedCornerShape(5.dp)
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
