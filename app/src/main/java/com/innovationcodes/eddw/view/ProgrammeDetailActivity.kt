package com.innovationcodes.eddw.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import kotlinx.android.synthetic.main.activity_programme_detail.*
import kotlinx.android.synthetic.main.content_programme_detail.*
import java.text.SimpleDateFormat
import java.util.*

class ProgrammeDetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programme_detail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val id = intent.getIntExtra("id", 0)
        val programme = ProgrammeViewModel.programs.value?.first { it.id == id } ?: return
        val sd = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val sDate = sd.parse(programme.startDate)
        val eDate = sd.parse(programme.endDate)
        val sdFormated = SimpleDateFormat("MMM d, HH:mm a", Locale.US)
        val edFormated = SimpleDateFormat("HH:mm a", Locale.US)
        tvProDetailTitle.text = programme.title
        tvProDetailDate.text = "${sdFormated.format(sDate)} - ${edFormated.format(eDate)}"
        tvProDetailLocation.text = programme.room.name
        tvProDetailInformation.text = programme.description
        tvProDetailSpeakerName.text = programme.speaker.fullname
    }
}
