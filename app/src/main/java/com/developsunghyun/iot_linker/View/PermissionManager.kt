package com.developsunghyun.iot_linker.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat

class PermissionManager(context: Context) {
    private val myContext = context

    //권한이 허용되어 있는지 확인하는 코드 허용되어 있다면 true를 반환 안되있다면 false를 반환
    fun checkPermission(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(myContext, permission) == PackageManager.PERMISSION_GRANTED
    }

    //권한을 요청하는 코드 만약 요청을 거부할 경우 재 요청
    fun requestPermission(permission: String, requestCode: Int) {
        when {
            // 이미 권한이 허용된 경우
            checkPermission(permission) -> {
                Toast.makeText(myContext, "권한이 이미 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }

            // 이전에 거부했으나 "다시 묻지 않음"이 선택되지 않은 경우
            ActivityCompat.shouldShowRequestPermissionRationale(myContext as Activity, permission) -> {
                ActivityCompat.requestPermissions(myContext, arrayOf(permission), requestCode)
            }

            else -> {
                // 처음 요청이거나 "다시 묻지 않음"이 선택된 상태
                ActivityCompat.requestPermissions(myContext, arrayOf(permission), requestCode)
            }
        }
    }

    //권한을 사용자가 직접 설정하는 코드 2번 이상 거부를 한다면 이후부터는 사용자가 직접 설정으로 이동해서 권한 제어
    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", myContext.packageName, null)
        ActivityCompat.startActivityForResult(myContext as Activity, intent, 1, null)
    }

}