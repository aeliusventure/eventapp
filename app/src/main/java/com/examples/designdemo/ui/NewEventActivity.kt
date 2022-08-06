package com.examples.designdemo.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.examples.designdemo.R
import com.examples.designdemo.adapter.ImageAdapter
import com.examples.designdemo.databinding.ActivityMainBinding
import com.examples.designdemo.databinding.ActivityNewEventBinding
import com.examples.designdemo.listener.OnClickListener
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList

class NewEventActivity : AppCompatActivity(), OnClickListener<String> {

    private val binding: ActivityNewEventBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityNewEventBinding.inflate(layoutInflater)
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(this)

            Glide.with(this)
                .load(uriContent)
                .into(binding.ivBackground)


        } else {
            // an error occurred
            val exception = result.error
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            Glide.with(this@NewEventActivity)
                .load("https://images.unsplash.com/photo-1659506888003-e494898cffd0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80")
                .into(ivBackground)

            rvUnsplash.layoutManager = GridLayoutManager(this@NewEventActivity, 3)

            //Populate Data for images
            val list = ArrayList<String>()
            list.add("https://images.unsplash.com/photo-1659506888003-e494898cffd0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80")
            list.add("https://images.unsplash.com/photo-1659382603427-bbbd561842c0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80")
            list.add("https://images.unsplash.com/photo-1659345182538-919fa885bc15?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80")
            list.add("https://images.unsplash.com/photo-1620641788421-7a1c342ea42e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1548&q=80")
            list.add("https://images.unsplash.com/photo-1569982175971-d92b01cf8694?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80")
            list.add("https://images.unsplash.com/photo-1523821741446-edb2b68bb7a0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80")
            list.add("https://images.unsplash.com/photo-1656267123457-3afa4a536665?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80")
            list.add("https://images.unsplash.com/photo-1655665436863-418c4f52e3d6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1606&q=80")
            list.add("")

            //Attach Adapter
            val adapter = ImageAdapter(list, this@NewEventActivity)
            rvUnsplash.adapter = adapter

            val spnList = ArrayList<String>()
            spnList.add("Days")
            spnList.add("Weeks")
            spnList.add("Months")


            val spnAdapter = ArrayAdapter(
                this@NewEventActivity,
                R.layout.simple_spinner_item, spnList
            )
            spnAdapter.setDropDownViewResource(
                R.layout
                    .simple_spinner_dropdown_item
            )
            spnTyps.adapter = spnAdapter
            spnTyps.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            tvFromDevice.setOnClickListener {
                tvFromDevice.isSelected = true
                tvFromUnsplash.isSelected = false
                rvUnsplash.visibility = GONE
                cropImage.launch(
                    options {
                        setGuidelines(CropImageView.Guidelines.ON)
                    }
                )
            }

            tvFromUnsplash.setOnClickListener {
                tvFromDevice.isSelected = false
                tvFromUnsplash.isSelected = true
                rvUnsplash.visibility = VISIBLE
            }

            rlDate.setOnClickListener {
                val cal = Calendar.getInstance()
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH)
                val day = cal.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(
                    this@NewEventActivity,
                    R.style.DatePickerDialog,
                    object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                            cal.set(Calendar.YEAR, p1)
                            cal.set(Calendar.MONTH, p2)
                            cal.set(Calendar.DAY_OF_MONTH, p3)

                            val dateFormatter = SimpleDateFormat("dd MMM yyyy")

                            tvDate.text = dateFormatter.format(cal.time)

                        }
                    }, year, month, day
                ).show()
            }

            ivClose.setOnClickListener {
                onBackPressed()
            }

        }

    }

    override fun onClick(t: String) {

        Glide.with(this)
            .load(t)
            .into(binding.ivBackground)
    }
}