package com.innovationcodes.eddw.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import kotlinx.android.synthetic.main.activity_choose_type.*

class ChooseTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_type)
        cardSpeaker.setOnClickListener {
            showActivity(0)
        }
        cardCompany.setOnClickListener {
            showActivity(1)
        }
        cardGuest.setOnClickListener {
            showActivity(2)
        }
    }


    private fun showActivity(id: Int) {
        val nextView = Intent()
        when (id) {
            0 -> {
                nextView.setClass(this, SpeakerLoginActivity::class.java)
            }
            1 -> {
                nextView.setClass(this, CompanyLoginActivity::class.java)
            }
            2 -> {
                nextView.setClass(this, GuestLoginActivity::class.java)
            }
        }
        startActivity(nextView)
    }
}
