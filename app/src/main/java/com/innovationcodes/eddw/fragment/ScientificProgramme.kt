package com.innovationcodes.eddw.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.ProgrammeAdapter
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import kotlinx.android.synthetic.main.activity_scentific_programme.*
import kotlinx.android.synthetic.main.activity_scentific_programme.view.*

class ScientificProgramme : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_scentific_programme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Scentific // HandsOn
        val type = arguments?.getString("type", "")

        ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)
        ProgrammeViewModel.programs.observe(this, Observer { list ->
            val layout = LinearLayoutManager(context)

            val adapter = ProgrammeAdapter(list.filter { it.type == type })
            scientificProgrammeRV.layoutManager = layout
            scientificProgrammeRV.adapter = adapter

        })
        view.btnProgrammeBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
