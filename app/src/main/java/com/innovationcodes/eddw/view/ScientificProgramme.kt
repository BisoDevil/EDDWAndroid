package com.innovationcodes.eddw.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.ProgrammeAdapter
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import kotlinx.android.synthetic.main.activity_scentific_programme.*

class ScientificProgramme : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scentific_programme)

        ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)
        ProgrammeViewModel.programs.observe(this, androidx.lifecycle.Observer {
            val layout = LinearLayoutManager(this)
            val adapter = ProgrammeAdapter(it)
            scientificProgrammeRV.layoutManager = layout
            scientificProgrammeRV.adapter = adapter

        })
        btnProgrammeBack.setOnClickListener {
            finish()
        }
    }
}
