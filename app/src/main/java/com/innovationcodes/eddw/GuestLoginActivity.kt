package com.innovationcodes.eddw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guest_login.*
import kotlinx.android.synthetic.main.activity_speaker_login.*

class GuestLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_login)
        btnGuestBack.setOnClickListener {
            finish()
        }
        txtRegister.setOnClickListener {
            val regView = Intent(this,GuestRegisterActivity::class.java)
            startActivity(regView)
        }
    }
}
