package com.innovationcodes.eddw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_speaker_login.*

class SpeakerLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_login)
        btnSpeakerBack.setOnClickListener {
            finish()
        }
        btnSpeakerLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val homeView = Intent(this, HomeActivity::class.java)
        startActivity(homeView)
    }
}
