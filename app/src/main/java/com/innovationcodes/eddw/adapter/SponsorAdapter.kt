package com.innovationcodes.eddw.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.Sponsor
import kotlinx.android.synthetic.main.sponsor_rv_item.view.*

class SponsorAdapter(private var items: List<Sponsor>) :
    RecyclerView.Adapter<SponsorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.sponsor_rv_item, parent, false)

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
        fun bindItems(item: Sponsor) {

            itemView.tvSponsorName.text = item.name
            itemView.tvSponsorInfo.text = item.information

        }

    }
}