package com.developsunghyun.iot_linker.View.Components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developsunghyun.iot_linker.R

@Preview(showBackground = true)
@Composable
fun ItemLayout(modifier: Modifier = Modifier){
    Surface(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
        shape = RoundedCornerShape(14.dp),
        shadowElevation = 3.dp,
        color = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    Log.d("LOG", "클릭")
                }
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Text(
                text = "Name",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 8.dp, top = 2.dp, bottom = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemLayout2(modifier: Modifier = Modifier){
    Surface(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .clickable {
                        Log.d("LOG", "클릭")
                    },
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Name",
                    fontSize = 14.sp,
                )
            }
        }
    }
}

