package com.innovationcodes.eddw.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_guest_login.*

class GuestLoginActivity : AppCompatActivity() {
    private lateinit var operations: ServerOperations
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_login)
        operations = ServerOperations(this)
        btnGuestBack.setOnClickListener {
            finish()
        }
        txtRegister.setOnClickListener {
            val regView = Intent(this, GuestRegisterActivity::class.java)
            startActivity(regView)
        }
        btnGuestLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        operations.loginGuest(
            txtUserName.text.toString(), txtPassword.text.toString()
        ) {
            if (it) {
                val homeView = Intent(this, HomeActivity::class.java)
                startActivity(homeView)
            } else {
                Toast.makeText(this, "Wrong badge number or password", Toast.LENGTH_LONG).show()
            }
        }

    }
}
