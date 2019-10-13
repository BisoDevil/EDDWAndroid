package com.innovationcodes.eddw.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.innovationcodes.eddw.R
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.image_slider_layout.view.*

class SliderAdapter(private val items: List<String>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout, null)

        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(holder: SliderAdapterVH, position: Int) {
        holder.onBind(items[position])
    }

    override fun getCount(): Int {
        return items.size
    }

    inner class SliderAdapterVH(var view: View) : SliderViewAdapter.ViewHolder(view) {
        fun onBind(item: String) {

            view.iv_auto_image_slider.setImageUrl(item)


        }

    }


}