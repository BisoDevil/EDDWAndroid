package com.innovationcodes.eddw.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations
import com.innovationcodes.eddw.model.MetaAssist
import com.wisnu.datetimerangepickerandroid.CalendarPickerView
import kotlinx.android.synthetic.main.activity_meta_assist.*
import java.text.SimpleDateFormat
import java.util.*


class MetaAssistActivity : AppCompatActivity() {

    private lateinit var operations: ServerOperations
    private val metadata = MetaAssist()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meta_assist)
        operations = ServerOperations(this)
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        calendar_view.init(Date(), nextYear.time)
            .inMode(CalendarPickerView.SelectionMode.RANGE)
            .withSelectedDate(Date())
        btnMetaSubmit.setOnClickListener {
            sendMetaAssist()
//        showHome()
        }
        btnSkip.setOnClickListener {
            showHome()
        }

    }


    private fun sendMetaAssist() {
        val sd = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        if (calendar_view.selectedDates.count() > 1) {
            metadata.accoStartDate = sd.format(calendar_view.selectedDates.first())
            metadata.accoEndDate = sd.format(calendar_view.selectedDates.last())
        } else {
            Toast.makeText(this, "Please select end date", Toast.LENGTH_SHORT).show()
            return
        }
        metadata.isTransportation = rdTransportationYes.isChecked
        metadata.room = if (rdRoomYes.isChecked) 0 else 1


        operations.createMetaAssist(metadata) {
            val checkView = Intent(this, CheckoutActivity::class.java)
            startActivity(checkView)
        }

    }

    private fun showHome() {
        val homeView = Intent(this, HomeActivity::class.java)
        startActivity(homeView)
    }
}
