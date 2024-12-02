package com.developsunghyun.iot_linker.View

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap


@Composable
fun captureBitmap(
    content: @Composable ()->Unit
) : () -> Bitmap {
    val context = LocalContext.current

    val composeView = remember { ComposeView(context) }

    //callback (최신 상태의 View로 Bitmap을 만든다)
    fun Bitmap(): Bitmap = composeView.drawToBitmap()

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    return ::Bitmap
}