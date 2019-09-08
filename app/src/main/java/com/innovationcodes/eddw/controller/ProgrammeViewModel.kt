package com.innovationcodes.eddw.controller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innovationcodes.eddw.model.Programme

class ProgrammeViewModel : ViewModel() {
    companion object {
        var programs = MutableLiveData<ArrayList<Programme>>()
    }

}