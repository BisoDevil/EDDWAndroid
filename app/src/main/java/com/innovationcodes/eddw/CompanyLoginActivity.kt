package com.innovationcodes.eddw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    }
}
