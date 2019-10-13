package com.innovationcodes.eddw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


class GenericAdapter<T>(
    @LayoutRes private var layoutView: Int,
    private var callback: (view: View, item: T, position: Int) -> Unit
) :
    RecyclerView.Adapter<GenericAdapter<T>.ViewHolder>() {

    private var items = listOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(layoutView, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setItem(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(item: T, position: Int) {
            callback(itemView, item, position)

        }
    }

}