package com.innovationcodes.eddw.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_company_login.*

class CompanyLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_login)
        btnCompanyBack.setOnClickListener {
            finish()
        }

        btnCompanyLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        ServerOperations(this).loginEmployee(txtUserName.text.toString()) {
            if (it) {
                val homeView = Intent(this, HomeActivity::class.java)
                startActivity(homeView)
            } else {
                Toast.makeText(this, "Check you badge number", Toast.LENGTH_LONG).show()
            }
        }
    }
}
