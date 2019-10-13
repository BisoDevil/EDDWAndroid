package com.innovationcodes.eddw.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations
import com.innovationcodes.eddw.view.HomeActivity
import kotlinx.android.synthetic.main.activity_speaker_login.*

class SpeakerLoginFragment : Fragment() {
    private lateinit var operations: ServerOperations

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_speaker_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        btnSpeakerLogin.setOnClickListener {
            login()
        }
    }


    private fun login() {
        operations.loginSpeaker(txtUserName.text.toString()) {
            if (it) {
                val homeView = Intent(context, HomeActivity::class.java)
                startActivity(homeView)
            } else {
                Toast.makeText(context, "Check you badge number", Toast.LENGTH_LONG).show()
            }
        }


    }
}
