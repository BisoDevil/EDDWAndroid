package com.innovationcodes.eddw.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.adapter.SliderAdapter
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import com.innovationcodes.eddw.controller.ServerOperations
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var operations: ServerOperations


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)


        view.imgEditUser.setOnClickListener {
            sendMail()
        }
        view.tvHomeFullName.setOnClickListener {
            operations.logout()

        }
        view.tvProgramme.setOnClickListener {
            val args = Bundle()
            args.putString("type", "Scentific")
            findNavController().navigate(R.id.action_navigation_home_to_scientificProgramme, args)
        }
        view.tvHandsOn.setOnClickListener {
            val args = Bundle()
            args.putString("type", "HandsOn")
            findNavController().navigate(R.id.action_navigation_home_to_scientificProgramme, args)
        }
        view.tvSpeakers.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_speakerActivity)
        }
        view.tvSponsor.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_sponsorActivity2)
        }
        view.tvFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_favorite)
        }
        view.tvTimeLine.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_timeLine)
        }
        view.tvNote.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_notes)
        }
        view.tvBooth.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_boothFragment)
        }
        view.tvChat.setOnClickListener {
            Toast.makeText(context, "Not yet", Toast.LENGTH_SHORT).show()
        }

        initSlider()
    }


    private fun sendMail() {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "test@mail.com", null
            )
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Edit my profile")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Kindly I need to edit my profile with...")
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        view!!.tvHomeFullName.text = "Hello,\n${operations.getFullName()}"
    }


    private fun initSlider() {
        val links = arrayListOf(
            "https://www.companieshistory.com/wp-content/uploads/2015/01/Novartis.jpg",
            "https://colombiareports.com/wp-content/uploads/2019/08/pfizer-1170x585.jpg",
            "https://wazayf4u.com/wp-content/uploads/2017/10/download-2_600x300.jpg"
        )
        val adapter = SliderAdapter(links)
        imageSlider.sliderAdapter = adapter
        imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.startAutoCycle()

    }


}