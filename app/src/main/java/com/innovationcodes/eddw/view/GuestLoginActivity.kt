package com.innovationcodes.eddw.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.innovationcodes.eddw.R
import kotlinx.android.synthetic.main.activity_guest_login.*

class GuestLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_login)
        btnGuestBack.setOnClickListener {
            finish()
        }
        txtRegister.setOnClickListener {
            val regView = Intent(this, GuestRegisterActivity::class.java)
            startActivity(regView)
        }
    }
}
