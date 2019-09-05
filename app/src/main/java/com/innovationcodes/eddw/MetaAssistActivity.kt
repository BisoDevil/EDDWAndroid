package com.innovationcodes.eddw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wisnu.datetimerangepickerandroid.CalendarPickerView
import kotlinx.android.synthetic.main.activity_meta_assist.*
import java.util.*


class MetaAssistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meta_assist)
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        calendar_view.init(Date(), nextYear.time)
            .inMode(CalendarPickerView.SelectionMode.RANGE)
            .withSelectedDate(Date())

    }
}
