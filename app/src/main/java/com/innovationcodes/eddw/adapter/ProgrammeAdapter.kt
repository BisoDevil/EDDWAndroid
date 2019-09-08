package com.innovationcodes.eddw.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.Programme
import com.innovationcodes.eddw.view.ProgrammeDetailActivity
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ProgrammeAdapter(private var items: List<Programme>) :
    RecyclerView.Adapter<ProgrammeAdapter.ViewHolder>() {
    val selected = arrayListOf<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
//        if (selected.contains(1)) {
//            holder.itemView.btnAddFavorite.setImageResource(R.drawable.ic_favorite)
//        } else {
//            holder.itemView.btnAddFavorite.setImageResource(R.drawable.ic_favorite_filled)
//        }


    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItems(item: Programme) {
            val startDate =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(item.startDate)
            val endDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(item.endDate)
            val sDate = SimpleDateFormat("hh:mm a", Locale.US).format(startDate)
            val eDate = SimpleDateFormat("hh:mm a", Locale.US).format(endDate)


            itemView.tvProgrammeTitle.text = item.title
            itemView.tvProgrammeLocation.text = item.room.name
            itemView.tvProgrammeDate.text = "$sDate - $eDate"
            itemView.tvProgrammeSpeakerName.text = item.speaker.fullname
            itemView.btnAddFavorite.setOnClickListener {
                itemView.btnAddFavorite.setImageResource(R.drawable.ic_favorite_filled)
            }
            itemView.setOnClickListener {
                val detailView = Intent(itemView.context, ProgrammeDetailActivity::class.java)
                detailView.putExtra("id", item.id)
                itemView.context.startActivity(detailView)
            }

        }

    }


}