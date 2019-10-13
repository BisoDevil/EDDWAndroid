package com.innovationcodes.eddw.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.GenericAdapter
import com.innovationcodes.eddw.fragment.auth.CompanyLoginFragment
import com.innovationcodes.eddw.fragment.auth.GuestLoginFragment
import com.innovationcodes.eddw.fragment.auth.SpeakerLoginFragment
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import kotlinx.android.synthetic.main.activity_choose_type.*
import kotlinx.android.synthetic.main.recycler_view_item_select_type.view.*


class ChooseTypeActivity : AppCompatActivity() {
    var pos = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_type)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        switchType(0)
        val types =
            listOf(R.drawable.speaker, R.drawable.company, R.drawable.guest)
        val titles = resources.getStringArray(R.array.UserTypes)
        val adapter =
            GenericAdapter<Int>(R.layout.recycler_view_item_select_type) { view, item, position ->
                view.typeImage.setImageResource(item)
                view.typeTitle.text = titles[position]

            }
        adapter.setItem(types)
        CardSnapHelper().attachToRecyclerView(selectTypeRecyclerView)
        selectTypeRecyclerView.adapter = adapter
        selectTypeRecyclerView.setHasFixedSize(true)
        val layout = (selectTypeRecyclerView.layoutManager as CardSliderLayoutManager)

        selectTypeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    return
                }
                if (layout.activeCardPosition == pos) {
                    return
                }
                switchType(layout.activeCardPosition)
                pos = layout.activeCardPosition
                println("Basem ${layout.activeCardPosition}")
            }


        })
        selectTypeRecyclerView.startLayoutAnimation()

    }

    @SuppressLint("SetTextI18n")
    private fun switchType(pos: Int) {
        val titles = resources.getStringArray(R.array.UserTypes)
        tvSwitcher.setText("${getString(R.string.choose_type)} ${titles[pos]}?")
        val manager = supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)


        when (pos) {
            0 -> {
                manager
                    .replace(
                        R.id.typeFrameLayout,
                        SpeakerLoginFragment()
                    )
            }
            1 -> {
                manager
                    .replace(
                        R.id.typeFrameLayout,
                        CompanyLoginFragment()
                    )
            }
            2 -> {
                manager
                    .replace(
                        R.id.typeFrameLayout,
                        GuestLoginFragment()
                    )
            }
        }
        manager.commitNow()

    }

}
