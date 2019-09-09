package com.innovationcodes.eddw.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (ServerOperations(this).isLogged()) {
                val chooseView = Intent(this, HomeActivity::class.java)
                startActivity(chooseView)
                finish()
            } else {
                val chooseView = Intent(this, ChooseTypeActivity::class.java)
                startActivity(chooseView)
                finish()
            }


        }, 3000L)
    }
}
