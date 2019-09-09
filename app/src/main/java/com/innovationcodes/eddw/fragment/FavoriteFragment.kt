package com.innovationcodes.eddw.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.ProgrammeAdapter
import com.innovationcodes.eddw.controller.DBOperations
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {
    private lateinit var operations: ServerOperations
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        val layout = LinearLayoutManager(context)
        val favorited = DBOperations(context!!).getFavorite()
        val filtered = ProgrammeViewModel.programs.value?.filter { favorited.contains(it.id) }
        val adapter = ProgrammeAdapter(filtered!!)
        view.favRecyclerView.layoutManager = layout
        view.favRecyclerView.adapter = adapter

    }

}
