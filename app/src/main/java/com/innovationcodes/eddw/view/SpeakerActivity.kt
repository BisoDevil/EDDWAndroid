package com.innovationcodes.eddw.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.SpeakerAdapter
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_speaker.*

class SpeakerActivity : AppCompatActivity() {
    private lateinit var operations: ServerOperations
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker)
        operations = ServerOperations(this)
        operations.retriveSpeakers {
            val layout = LinearLayoutManager(this)
            val divider = DividerItemDecoration(this, layout.orientation)
            val adapter = SpeakerAdapter(it)
            speakerRV.layoutManager = layout
            speakerRV.adapter = adapter
            speakerRV.addItemDecoration(divider)
        }
        btnSpeakerBack.setOnClickListener {
            finish()
        }
    }
}
