package com.innovationcodes.eddw.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.DBOperations
import com.innovationcodes.eddw.controller.ProgrammeViewModel
import com.innovationcodes.eddw.controller.ServerOperations
import com.innovationcodes.eddw.model.Programme
import com.innovationcodes.eddw.model.Question
import kotlinx.android.synthetic.main.activity_programme_detail.*
import kotlinx.android.synthetic.main.content_programme_detail.*
import kotlinx.android.synthetic.main.question_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class ProgrammeDetailActivity : AppCompatActivity() {
    private lateinit var dbOperations: DBOperations
    private lateinit var operations: ServerOperations
    private var id: Int = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programme_detail)
        setSupportActionBar(toolbar)
        dbOperations = DBOperations(this)
        operations = ServerOperations(this)
        toolbar.setNavigationOnClickListener { finish() }

        id = intent.getIntExtra("id", 0)
        val programme = ProgrammeViewModel.programs.value?.first { it.id == id } ?: return
        val sd = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val sDate = sd.parse(programme.startDate)
        val eDate = sd.parse(programme.endDate)
        val sdFormated = SimpleDateFormat("MMM d, HH:mm a", Locale.US)
        val edFormated = SimpleDateFormat("HH:mm a", Locale.US)
        tvProDetailTitle.text = programme.title
        tvProDetailDate.text = "${sdFormated.format(sDate)} - ${edFormated.format(eDate)}"
        tvProDetailLocation.text = programme.room.name
        tvProDetailInformation.text = programme.description
        tvProDetailSpeakerName.text = programme.speaker.fullname
        txtProDetailNote.setText(dbOperations.getNote(id))
        btnProDetailNote.setOnClickListener {
            saveNote(programme)
        }
        btnProDetailAttendanceCode.setOnClickListener {
            saveAttendance()
        }
        fabSendQuestion.setOnClickListener {
            askQuestion()
        }
    }


    private fun saveNote(programme: Programme) {

        dbOperations.saveNote(
            txtProDetailNote.text.toString(),
            programme.title,
            programme.id
        )
    }

    private fun saveAttendance() {
        operations.saveAttendance(txtProDetailAttendanceCode.text.toString(), id)

    }

    @SuppressLint("InflateParams")
    private fun askQuestion() {

        val dialog = AlertDialog.Builder(this)
//        dialog.window!!.setBackgroundDrawableResource(R.drawable.rounded_background)
        dialog.setTitle("Question")
        val view = layoutInflater.inflate(R.layout.question_layout, null)
        val input = view.txtQuestion
        dialog.setView(view)
        dialog.setPositiveButton("OK") { oDialog, _ ->
            if (input.text.toString().isEmpty()) {
                Toast.makeText(this, "Nothing asked", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            val q = Question()
            operations.saveQuestion(q)
            oDialog.dismiss()
        }
        dialog.setNegativeButton("Cancel") { cDialog, _ ->
            cDialog.cancel()
        }
        dialog.create()
        dialog.show()


    }
}
