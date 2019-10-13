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
import com.innovationcodes.eddw.adapter.GenericAdapter
import com.innovationcodes.eddw.controller.ServerOperations
import com.innovationcodes.eddw.model.BoothBook
import kotlinx.android.synthetic.main.fragment_booth.view.*
import kotlinx.android.synthetic.main.recycler_view_item_speaker.view.*


class BoothFragment : Fragment() {
    private lateinit var operations: ServerOperations
    private lateinit var adapter: GenericAdapter<BoothBook>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        initRecyclerView()
        operations.retrieveBooth {
            adapter.setItem(it)
        }
        view.btnBoothBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initRecyclerView() {
        adapter =
            GenericAdapter(R.layout.recycler_view_item_speaker) { view, item, _ ->
                view.circleImage.setImageResource(R.drawable.booth)
                view.tvSpeakerName.text = item.sponsor.name
                view.tvSpeakerInfo.text = item.booth.name
            }
        val layout = LinearLayoutManager(context)
        view!!.boothRV.adapter = adapter
        view!!.boothRV.layoutManager = layout
        view!!.boothRV.addItemDecoration(DividerItemDecoration(context, layout.orientation))
    }
}
