package com.innovationcodes.eddw.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import com.innovationcodes.eddw.controller.ServerOperations

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        ServerOperations(this).retrieveProgramme {
            ProgrammeViewModel.programs.postValue(it)

        }
    }
}
