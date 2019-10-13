package com.innovationcodes.eddw.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.SpeakerAdapter
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_speaker.*

class SpeakerActivity : Fragment() {
    private lateinit var operations: ServerOperations


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_speaker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        operations.retriveSpeakers {
            val layout = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, layout.orientation)
            val adapter = SpeakerAdapter(it)
            speakerRV.layoutManager = layout
            speakerRV.adapter = adapter
            speakerRV.addItemDecoration(divider)
        }
        btnSpeakerBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
