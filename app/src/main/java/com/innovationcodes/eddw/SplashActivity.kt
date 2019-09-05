package com.innovationcodes.eddw

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val chooseView = Intent(this, ChooseTypeActivity::class.java)
            startActivity(chooseView)
            finish()
        }, 3000L)
    }
}
