package com.examples.designdemo.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.openActivity(activity: Class<*>?) {
    val intent = Intent(this, activity)
    startActivity(intent)
}