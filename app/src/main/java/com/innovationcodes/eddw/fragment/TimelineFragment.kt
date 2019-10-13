package com.innovationcodes.eddw.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.TimelineAdapter
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.fragment_timeline.view.*


class TimelineFragment : Fragment() {

    private lateinit var operations: ServerOperations
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        operations.retrieveTimeline {
            val layout = LinearLayoutManager(context)
            val adapter = TimelineAdapter(it)
            view.timelineRecyclerView.layoutManager = layout
            view.timelineRecyclerView.adapter = adapter
        }
        view.btnTimeLineBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}
