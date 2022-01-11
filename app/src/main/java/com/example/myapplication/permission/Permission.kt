package com.example.myapplication.permission

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.Intent
import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class Permission(private val activity: Activity){

    private val requestCode : Int = 100;

    fun onRequestPermissionResult(requestCode: Int, permission: Array<out String>, grantResults:IntArray, callback: Runnable){
        if(requestCode != requestCode){
            return;
        }
        callback.run();
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun request(){
        val permissions: Array<String> = arrayOf(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        );

        activity.requestPermissions(permissions, requestCode)
    }
}