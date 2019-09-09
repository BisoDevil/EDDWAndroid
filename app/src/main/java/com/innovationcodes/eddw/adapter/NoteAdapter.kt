package com.innovationcodes.eddw.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.Note
import com.innovationcodes.eddw.view.ProgrammeDetailActivity
import kotlinx.android.synthetic.main.recycler_view_item_note.view.*

class NoteAdapter(private var items: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_note, parent, false)

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
        fun bindItems(item: Note) {
            itemView.tvNoteProgrammeName.text = item.proName
            itemView.tvNoteProgrammeBody.text = item.body
            itemView.setOnClickListener {
                val detailView = Intent(itemView.context, ProgrammeDetailActivity::class.java)
                detailView.putExtra("id", item.proId)
                itemView.context.startActivity(detailView)
            }
        }

    }
}