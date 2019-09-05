package com.innovationcodes.eddw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_guest_register.*

class GuestRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_register)
        btnRegisterBack.setOnClickListener {
            finish()
        }
        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val metaView = Intent(this, MetaAssistActivity::class.java)
        startActivity(metaView)
        finish()
    }
}
