package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.permission.Permission

class LoadingActivity : AppCompatActivity() {

    private val permission: Permission = Permission(this);

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        permission.request();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permission.onRequestPermissionResult(requestCode, resultCode, data) {
            val intent = Intent(this, LoginActivity::class.java)
            finish();
            startActivity(intent);
        }
    }

}