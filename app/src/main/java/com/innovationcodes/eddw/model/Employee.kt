package com.innovationcodes.eddw.model

class Employee {
    var id: String = ""
    var fullname: String = ""
    var username: String = ""
    var companyId: Int = 0
    var company: Company = Company()
    var email: String = ""
    var phone: String = ""
    var title: Int = 0
    var speciality: String = ""
    var type: Int = 0
    var messageToken: String = ""
    var token: String = ""
}

class Company {
    var name: String = ""
}