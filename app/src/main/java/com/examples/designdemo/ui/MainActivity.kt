package com.examples.designdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.examples.designdemo.databinding.ActivityMainBinding
import com.examples.designdemo.utils.openActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding) {

            tvUpcoming.isSelected = true

            ivAdd.setOnClickListener {
                openActivity(NewEventActivity::class.java)
            }

        }


    }


}