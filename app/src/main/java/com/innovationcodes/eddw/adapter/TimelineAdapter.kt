package com.innovationcodes.eddw.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.Timeline
import kotlinx.android.synthetic.main.timeline_rv_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class TimelineAdapter(private var items: List<Timeline>) :
    RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.timeline_rv_item, parent, false)

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
        fun bindItems(item: Timeline) {
            val startDate =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(item.startDate)
            val endDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(item.endDate)

            val sDate = SimpleDateFormat("hh:mm a", Locale.US).format(startDate)
            val eDate = SimpleDateFormat("hh:mm a", Locale.US).format(endDate)
            val day = SimpleDateFormat("dd\nMMM", Locale.US).format(startDate)
            itemView.tvTimelineDate.text = day
            itemView.tvTimelineTime.text = "$sDate - $eDate"
            itemView.tvTimelineTitle.text = item.title
            itemView.tvTimelineDescription.text = item.information

        }

    }
}
