package com.innovationcodes.eddw.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import com.innovationcodes.eddw.controller.ServerOperations

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setupWithNavController(navController)
        ServerOperations(this).retrieveProgramme {
            ProgrammeViewModel.programs.postValue(it)

        }
    }
}
