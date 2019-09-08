package com.innovationcodes.eddw.model

class Programme {
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var startDate: String = ""
    var endDate: String = ""
    var speaker: Speaker = Speaker()
    var attendanceCode: String = ""
    var room: Room = Room()
    var status: Int = 0
}

