package com.innovationcodes.eddw.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.SponsorAdapter
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_sponsor.*

class SponsorActivity : AppCompatActivity() {

    private lateinit var operations: ServerOperations
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsor)
        operations = ServerOperations(this)
        operations.retrieveSponsors {
            val layout = LinearLayoutManager(this)
            val adapter = SponsorAdapter(it)
            sponsorRV.layoutManager = layout
            sponsorRV.adapter = adapter
        }
        btnSponsorBack.setOnClickListener {
            finish()
        }
    }
}
