package com.innovationcodes.eddw.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.NoteAdapter
import com.innovationcodes.eddw.controller.DBOperations
import kotlinx.android.synthetic.main.fragment_notes.view.*

/**
 * A simple [Fragment] subclass.
 */
class NoteFragment : Fragment() {
    private lateinit var dbOperations: DBOperations

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbOperations = DBOperations(context!!)

    }


    override fun onStart() {
        super.onStart()
        val layout = LinearLayoutManager(context)
        val notes = dbOperations.getAllNote()
        val adapter = NoteAdapter(notes)
        val decoration = DividerItemDecoration(context, layout.orientation)
        view!!.notesRecyclerView.layoutManager = layout
        view!!.notesRecyclerView.adapter = adapter
        view!!.notesRecyclerView.addItemDecoration(decoration)
    }

}
