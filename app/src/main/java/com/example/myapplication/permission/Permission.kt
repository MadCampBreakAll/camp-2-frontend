package com.example.myapplication.permission

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.Intent
import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class Permission(private val activity: Activity){

    private val requestCode : Int = 1;

    fun onRequestPermissionResult(requestCode: Int, resultCode: Int, data: Intent?, callback: Runnable){
        if(this.requestCode != requestCode){
            return;
        }
        println("Hello");
        callback.run();
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun request(){
        val permissions: Array<String> = arrayOf(
            android.Manifest.permission.INTERNET
        );

        activity.requestPermissions(permissions, requestCode);
    }
}