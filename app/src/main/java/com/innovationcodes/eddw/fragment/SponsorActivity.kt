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
import com.innovationcodes.eddw.adapter.SponsorAdapter
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_sponsor.*

class SponsorActivity : Fragment() {

    private lateinit var operations: ServerOperations


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_sponsor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        operations.retrieveSponsors {
            val layout = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, layout.orientation)
            val adapter = SponsorAdapter(it)
            sponsorRV.layoutManager = layout
            sponsorRV.adapter = adapter
            sponsorRV.addItemDecoration(divider)
        }
        btnSponsorBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
