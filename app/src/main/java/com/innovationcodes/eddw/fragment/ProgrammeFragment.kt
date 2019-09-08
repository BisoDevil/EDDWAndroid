package com.innovationcodes.eddw.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.ProgrammeAdapter
import com.innovationcodes.eddw.model.Programme
import kotlinx.android.synthetic.main.fragment_prgramme.view.*


class ProgrammeFragment : Fragment() {


    var list = listOf<Programme>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prgramme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProgrammeAdapter(list)
        val layout = LinearLayoutManager(context)
        view.programmeRV.layoutManager = layout
        view.programmeRV.adapter = adapter

    }


}
