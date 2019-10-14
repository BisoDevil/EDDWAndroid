package com.innovationcodes.eddw.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.Speaker
import kotlinx.android.synthetic.main.recycler_view_item_speaker.view.*

class SpeakerAdapter(private var items: List<Speaker>) :
    RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_speaker, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])


    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItems(item: Speaker) {
            itemView.tvSpeakerName.text = item.fullname
            itemView.tvSpeakerInfo.text = item.country

        }

    }
}