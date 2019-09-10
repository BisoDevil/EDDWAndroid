package com.innovationcodes.eddw.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import com.innovationcodes.eddw.controller.ServerOperations
import com.innovationcodes.eddw.model.Programme
import com.innovationcodes.eddw.view.ScientificProgramme
import com.innovationcodes.eddw.view.SpeakerActivity
import com.innovationcodes.eddw.view.SponsorActivity
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var operations: ServerOperations


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        ProgrammeViewModel.programs.observe(this, androidx.lifecycle.Observer {
            println("Basem observe")
            val homeAdapter = DashboardPagerAdapter(childFragmentManager, it)
            root.viewerHome.adapter = homeAdapter
            root.tabHomeLayout.setupWithViewPager(root.viewerHome, true)
            root.tabHomeLayout.getTabAt(1)?.select()
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations = ServerOperations(context!!)
        view.cardHomeProgramme.setOnClickListener {
            showAllProgrammes()
        }
        view.cardHomeSponsors.setOnClickListener {
            showAllSponsors()
        }
        view.cardHomeSpeakers.setOnClickListener {
            showAllSpeaker()
        }

        view.imgEditUser.setOnClickListener {
            sendMail()
        }


        setupSearchForProgramme()
    }

    private fun showAllProgrammes() {
        val pro = Intent(context!!, ScientificProgramme::class.java)
        startActivity(pro)
    }

    private fun showAllSponsors() {
        val pro = Intent(context!!, SponsorActivity::class.java)
        startActivity(pro)
    }

    private fun showAllSpeaker() {
        val pro = Intent(context!!, SpeakerActivity::class.java)
        startActivity(pro)
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

    private fun setupSearchForProgramme() {

//        view!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })
    }


    inner class DashboardPagerAdapter(
        fm: FragmentManager,
        var allProgrammes: ArrayList<Programme>
    ) : FragmentPagerAdapter(fm) {

        private val arrFragemt = getFragments()
        private val arrTitles = resources.getStringArray(R.array.tab_titles)
        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return arrFragemt[position]
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return arrFragemt.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return arrTitles[position]
        }

        private fun getFragments(): ArrayList<Fragment> {
            val arrFragment = arrayListOf<Fragment>()
            val grouped = allProgrammes.groupBy { it.status }

            val running = ProgrammeFragment()
            running.list = grouped[1] ?: listOf()
            arrFragment.add(running)

            val upComing = ProgrammeFragment()
            upComing.list = grouped[0] ?: listOf()
            arrFragment.add(upComing)
            val finished = ProgrammeFragment()
            finished.list = grouped[2] ?: listOf()
            arrFragment.add(finished)

            return arrFragment
        }
    }

}