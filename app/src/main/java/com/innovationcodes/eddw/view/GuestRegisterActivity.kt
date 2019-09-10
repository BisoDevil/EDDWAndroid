package com.innovationcodes.eddw.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations
import com.innovationcodes.eddw.model.Guest
import kotlinx.android.synthetic.main.activity_guest_register.*

class GuestRegisterActivity : AppCompatActivity() {
    private lateinit var operations: ServerOperations
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_register)
        operations = ServerOperations(this)
        btnRegisterBack.setOnClickListener {
            finish()
        }
        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val guest = Guest()
        guest.fullname = txtFullName.text.toString()
        guest.phone = txtPhoneNumber.text.toString()
        guest.email = txtEmail.text.toString()
        guest.title = spTitle.selectedItemPosition
        guest.speciality = txtSpeciality.text.toString()
        guest.country = spCountry.selectedItem.toString()
        guest.password = txtPassword.text.toString()
        operations.registerGuest(guest) {
            if (it) {
                Toast.makeText(
                    this,
                    "Your credentials sent to you mail address",
                    Toast.LENGTH_SHORT
                ).show()
                val metaView = Intent(this, MetaAssistActivity::class.java)
                startActivity(metaView)
                finish()
            } else {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
